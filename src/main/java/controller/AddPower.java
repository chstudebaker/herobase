package controller;

import entity.Powers;
import persistance.PowersDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/AddPowers")
public class AddPower extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve power descriptions from the database
        PowersDao powersDao = new PowersDao();
        List<String> powerDescriptions = powersDao.getAllDescriptions();

        // Eliminate duplicate power descriptions using a HashSet
        Set<String> uniquePowerDescriptions = new HashSet<>(powerDescriptions);

        // Set unique power descriptions in request attributes
        request.setAttribute("powerDescriptions", uniquePowerDescriptions);

        // Forward the request to the JSP
        request.getRequestDispatcher("addPower.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroID = request.getParameter("heroID"); // Retrieve heroID from the form
        String selectedPower = request.getParameter("selectedPower"); // Retrieve selected power from the form
        String customPower = request.getParameter("customPower"); // Retrieve custom power from the form
        String explanation = request.getParameter("explanation");

        // Create a new instance of Powers entity
        Powers power = new Powers();
        power.setDescription(selectedPower != null && !selectedPower.isEmpty() ? selectedPower : customPower);
        power.setExplanation(explanation); // Set the explanation field

        // Set the HeroID on the Powers entity
        if (heroID != null && !heroID.isEmpty()) {
            power.setHeroID(Integer.parseInt(heroID));
        }

        // Assuming you have a PowersDao for powers-related operations
        PowersDao powersDao = new PowersDao();

        // Save the Powers entity
        Integer insertedPowerId = powersDao.insert(power);
        boolean success = insertedPowerId != null && insertedPowerId > 0;

        if (success) {
            // Redirect to a success page or display a success message
            response.sendRedirect("addPowersSuccess.jsp");
        } else {
            // Handle insertion failure
            response.sendRedirect("addPowersFailure.jsp");
        }
    }
}
