package com.ashsample.androidconcepts.github;

//https://guides.codepath.com/android/consuming-apis-with-retrofit
//http://www.jsonschema2pojo.org/
//https://www.baeldung.com/retrofit

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public final class GitHubRepo {
     private static  GitHubRepo INSTANCE;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHIUB_BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build();
   private GitHubService service = retrofit.create(GitHubService.class);
     private static final String GITHIUB_BASE_URL = "https://api.github.com";
     private String GET_USRES_URL = "https://api.github.com/users?since=1";
    //https://github.com/square/retrofit/blob/master/samples/src/main/java/com/example/retrofit/SimpleService.java
     static{
         INSTANCE = new GitHubRepo();
     }
    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }
      public interface GitHubService{
          @GET("users")
          Call<List<UserPoJo>> getAllUsers(@Query("since") int since);
          @GET("users/{username}")
          Call<UserPoJo> getUserDetails(@Path("username") String username1);
          @GET("/repos/{owner}/{repo}/contributors")
          Call<List<Contributor>> getContributorsforRepo(
                  @Path("owner") String owner,
                  @Path("repo") String repo);
      }
     private GitHubRepo(){}

     public List<UserPoJo>getALLUsers(){

        //return fetchUsersUsingURLConnection();
         return fetchUsingRetrofit();

     }

     public List<Contributor> getContributorsforRepo(){

        try {
            // Create a call instance for looking up Retrofit contributors.
            Call<List<Contributor>> call = service.getContributorsforRepo("square", "retrofit");

            // Fetch and print a list of the contributors to the library.
            List<Contributor> contributors = call.execute().body();
            for (Contributor contributor : contributors) {
                System.out.println(contributor.login + " (" + contributor.contributions + ")");
            }
            return contributors;
        }catch (Exception e){
            return null;
        }

     }

     public UserPoJo getUserDetail(String username){
         try {
             Call<UserPoJo> call = service.getUserDetails("ashiqsayyad");
             UserPoJo user = call.execute().body();
             return user;
         }catch (Exception e){
             return null;
         }
     }

     public List<UserPoJo> fetchUsingRetrofit(){
         try {


             Call<List<UserPoJo>> call = service.getAllUsers(135);
             // Fetch and print a list of the contributors to the library.
             List<UserPoJo> users = call.execute().body();
             for (UserPoJo user : users) {
                 System.out.println("user::" + user.getName());
             }
             return users;
         }catch (Exception e){
             return null;
         }
     }

     public ArrayList<UserPoJo>fetchUsersUsingURLConnection(){
         try {
             StringBuffer str = new StringBuffer();
             URL temp = new URL(GET_USRES_URL);
             HttpURLConnection urlConnection = (HttpURLConnection) temp.openConnection();
             //  if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED){
             BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

             String tempstr;
             while((tempstr =reader.readLine() )!= null){
                 str.append(tempstr);
             }

             // }
             if(str.length() > 0){ ArrayList<UserPoJo> users = new ArrayList<UserPoJo>();
                 users.add(new UserPoJo());
                 return users;
             }
             return null;


         }catch(Exception e){
             return null;
         }

     }

     public static GitHubRepo getInstance(){
         return INSTANCE;
     }





}
