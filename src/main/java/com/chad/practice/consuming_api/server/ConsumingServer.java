package com.chad.practice.consuming_api.server;

import com.chad.practice.consuming_api.model.Forms;
import com.chad.practice.consuming_api.model.Meta;
import com.chad.practice.consuming_api.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ConsumingServer {
    @Autowired
    RestTemplate rt;

    public Forms getAll(){
        Forms dataInfo = rt.getForObject("https://gorest.co.in/public-api/users", Forms.class);

        return dataInfo;

    }

    public String getCode(){
        Forms dataInfo = rt.getForObject("https://gorest.co.in/public-api/users", Forms.class);
        return dataInfo.getCode();

    }

    public Meta getMeta(){
        Forms dataInfo = rt.getForObject("https://gorest.co.in/public-api/users", Forms.class);
        return dataInfo.getMeta();

    }

    public List<User> getUserList(){
        ArrayList<User>userList = new ArrayList<>();
        Forms dataInfo = rt.getForObject("https://gorest.co.in/public-api/users", Forms.class);
        int pages = getMeta().getPagination().getPages();
        //int page = getMeta().getPagination().getPage();
        String tmp = "https://gorest.co.in/public-api/users?page=";
        for(int i = 1; i<=pages; i++){
            String url = tmp + i;
            //System.out.println(url);
            Forms pageInfo = rt.getForObject(url, Forms.class);
            for(User user: pageInfo.getData()){
                userList.add(user);
//                if(user.getGender().equals("Male") ){
//                    userList.add(user);
//                }
            }

        }

        return userList;
    }

    public User addUser(User user) {
        String url = "https://gorest.co.in/public-api/users";
        User tmp = rt.postForObject(url, user, User.class);
        return tmp;
    }

    public String updateUser(String id, User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = "3cca4ec9633c68ab984d64b100fa6bceadff2508843b87be6b454e3d36555d84";
        headers.add("Authorization", "Bearer "+ token );

        String url = "https://gorest.co.in/public-api/users/"+id;

        //**Important** have to transfer User type to json format!!!!!
        //reference from: https://github.com/DucManhPhan/J2EE/blob/master/src/Utils/spring-resttemplate-utils/src/main/java/com/manhpd/controller/TestRestTemplateController.java
        String bodyData = new Gson().toJson(user);
        HttpEntity<String> httpEntity = new HttpEntity<String>(bodyData, headers);

//        solution#1 Using RestTemplate exchange() method
        ResponseEntity<String> responseEntity = rt.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        return responseEntity.getBody();

//        solution#2 Using RestTemplate put() method
//        rt.put(url,httpEntity);
//        return "down";
    }

    public String deleteUser(String id) {
        String url = "https://gorest.co.in/public-api/users/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //reference for Authentication failed: https://stackoverflow.com/questions/52784834/setting-authorization-header-in-spring-resttemplate/52786520#52786520
        String token = "3cca4ec9633c68ab984d64b100fa6bceadff2508843b87be6b454e3d36555d84";
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

//        solution#1 Using RestTemplate exchange() method
        ResponseEntity<String> responseEntity = rt.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
        return responseEntity.getBody();

//        solution#2 Using RestTemplate put() method
//        rt.delete(url);
//        return "down";

    }

}
