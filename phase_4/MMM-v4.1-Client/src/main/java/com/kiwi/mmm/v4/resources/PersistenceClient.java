/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kiwi.mmm.v4.client.models.Categories;
import com.kiwi.mmm.v4.client.models.Operations;

import com.kiwi.mmm.v4.client.models.Users;
import com.kiwi.mmm.v4.exceptions.AlreadyExistsException;
import com.kiwi.mmm.v4.exceptions.DoesNotExistException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.List;


/**
 *
 * @author laura
 */
public class PersistenceClient {

    private static final String         USERS_URL = "http://localhost:8080/MMM-v4.1-Services/resources/user";
    private static final String         OPERATIONS_URL = "http://localhost:8080/MMM-v4.1-Services/resources/operation";
    private static final String         CATEGORIES_URL = "http://localhost:8080/MMM-v4.1-Services/resources/category";
    
    private static Client               client;
    private static PersistenceClient    instance;
    
    
    private PersistenceClient() 
    {
        PersistenceClient.client = ClientBuilder.newClient();
    }

    public static PersistenceClient getInstance() {
        if (instance == null) {
            instance = new PersistenceClient();
        }
        return instance;
    }
    
    /* ---------------------------------------------------------- */
    /* --------------------- Users requests --------------------- */
    /* ---------------------------------------------------------- */
    
    public Users checkPassword(String username, int password) throws DoesNotExistException {
    Users u = getUsersByName(username);
    if (u != null && u.getUsername().equals(username) && u.getPassword() == password) {
        return u;
    }
    throw new DoesNotExistException("User " + username + " does not exist or incorrect password.");
}
    
    
    public Users getUsersByName(String username) {
        Users u = parseUser(client.target(USERS_URL + "/findByName/" + username).request().get(String.class));
        return u;
    }
    
    private List<Users> parseUserList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Users>>() {
        }.getType());
    }

    private Users parseUser(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, Users.class);
    }
    
    public void updateUser(Users user) {
        client.target(USERS_URL + "/edit/" + user.getUserId()).request().put(Entity.entity(user, "application/json"));
    }
    
    
    public void createUser(Users user) {
        WebTarget target = client.target(USERS_URL + "/create");
        Entity theEntity = Entity.entity(user, "application/json");
        Response response = target.request().post(theEntity);
    }
    
    public boolean emailExists(String email) throws AlreadyExistsException {
        return client.target(USERS_URL + "/emailExists/" + email).request().get().readEntity(Boolean.class);
    }
    
    /*public void deleteUser(Users user) {
        WebTarget target = client.target(USERS_URL + "/remove/" + user.getUserId());

        Response response = target.request().delete();

        response.close();
    }*/

    
    
    /* ---------------------------------------------------------- */
    /* ---------------- Operations requests --------------------- */
    /* ---------------------------------------------------------- */
    
    private List<Operations> parseOperationsList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Operations>>() {
        }.getType());
    }

    private Operations parseOperation(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, Operations.class);
    }
    
    public List<Operations> getAllOperations() {
        return parseOperationsList(client.target(OPERATIONS_URL + "/findAll").request().get(String.class));
    }
    
    public List<Operations> getAllUserOperations(Integer userId) {
        List<Operations> operationsList = parseOperationsList(client.target(OPERATIONS_URL + "/findByUserId/" + userId).request().get(String.class));
        return operationsList;
    }
    
    public void createOperation(Operations operation) {
        WebTarget target = client.target(OPERATIONS_URL + "/create");
        Entity theEntity = Entity.entity(operation, "application/json");
        Response response = target.request().post(theEntity);
    }
    
    public void deleteOperation(Operations operation) {
        WebTarget target = client.target(OPERATIONS_URL + "/remove/" + operation.getOperationId());

        Response response = target.request().delete();

        if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            // La suppression a échoué ou a rencontré une erreur
            throw new RuntimeException("Failed to delete operation. Status code: " + response.getStatus());
        }

        // Fermez la réponse pour libérer les ressources
        response.close();
    }

    /* ---------------------------------------------------------- */
    /* ---------------- Categories requests --------------------- */
    /* ---------------------------------------------------------- */
    
    private List<Categories> parseCategoriesList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Categories>>() {
        }.getType());
    }

    private Categories parseCategory(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, Categories.class);
    }
    
    
    public List<Categories> getAllCategories() {
        return parseCategoriesList(client.target(CATEGORIES_URL + "/findAll").request().get(String.class));
    }
    
    
    
}
