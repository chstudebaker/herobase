package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.util.FileUploadHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/EditHero")
public class EditHero extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteHero.class);


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            String heroIDParam = request.getParameter("heroId");
            if (heroIDParam == null || heroIDParam.isEmpty()) {
                logger.log(Level.INFO, "the heroId is: " + heroIDParam);
                response.sendRedirect("error400.jsp");
                return;
            }
            int heroID = Integer.parseInt(heroIDParam);
            HeroDao heroDao = new HeroDao();
            Hero hero = heroDao.getById(heroID);
            if (hero == null) {
                response.sendRedirect("error500.jsp");
                return;
            }
            request.setAttribute("hero", hero);
            request.getRequestDispatcher("editHero.jsp").forward(request, response);
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request to edit a hero
        editHero(request, response);
    }
    protected void editHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");
        String heroIDParam = request.getParameter("heroId");
        logger.info("UserID: " + userID);
        logger.info("HeroIDParam: " + heroIDParam);

        if (heroIDParam == null || heroIDParam.isEmpty()) {
            logger.error("HeroID is null or empty");
            response.sendRedirect("error400.jsp");
            return;
        }

        int heroId = Integer.parseInt(heroIDParam);

        String codeName = request.getParameter("codeName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String descriptions = request.getParameter("descriptions");
        String personality = request.getParameter("personality");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        Part filePart = request.getPart("images");
        Part emblemPart = request.getPart("emblem");

        logger.info("Code Name: " + codeName);
        logger.info("Real Name: " + realName);
        logger.info("Bio: " + bio);
        logger.info("Alignment: " + alignment);
        logger.info("Descriptions: " + descriptions);
        logger.info("Personality: " + personality);
        logger.info("Height: " + height);
        logger.info("Weight: " + weight);

        // File upload handling logic
        String images = null;
        if (filePart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            images = fileUploadHandler.handleFileUpload(filePart);
        } else {
            HeroDao heroDao = new HeroDao();
            Hero existingHero = heroDao.getById(heroId);
            images = existingHero.getImages();
        }

        String emblem = null;
        if (emblemPart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            emblem = fileUploadHandler.handleFileUpload(emblemPart);
        } else {
            HeroDao heroDao = new HeroDao();
            Hero existingHero = heroDao.getById(heroId);
            emblem = existingHero.getEmblem();
        }

        // Create updatedHero object and set values
        Hero updatedHero = new Hero();
        updatedHero.setHeroId(heroId);
        updatedHero.setCodeName(codeName);
        updatedHero.setRealName(realName);
        updatedHero.setBio(bio);
        updatedHero.setAlignment(alignment);
        updatedHero.setDescriptions(descriptions);
        updatedHero.setPersonality(personality);
        updatedHero.setHeight(height);
        updatedHero.setWeight(weight);
        updatedHero.setImages(images);
        updatedHero.setEmblem(emblem);

        // Log updated hero information
        logger.info("Updated Hero: " + updatedHero);

        // Update hero in the database
        HeroDao heroDao = new HeroDao();
        boolean success = heroDao.update(updatedHero);

        // Set attributes for request dispatcher
        request.setAttribute("success", success);
        request.setAttribute("editedItemId", heroId);
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);
    }

}
