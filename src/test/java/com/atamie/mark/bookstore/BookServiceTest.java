package com.atamie.mark.bookstore;

import com.atamie.mark.bookstore.dtos.BookOperationResponse;
import com.atamie.mark.bookstore.utils.ResponseCodes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class BookServiceTest {

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;


    @Before
    public void configureRestAssured() {

        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;

    }

    /**
     * successful addition of book to the bookstore rest system
     */
    @Test
    public void registerBook()
    {
        var requestPayload = Map.of("isbn","123456","title","Java for dummies","authorId",1,"publicationDate","2023-12-20","price",2500.00,"quantity",5);
        JSONObject jsonObject = new JSONObject(requestPayload);
        String jsonData = jsonObject.toString();
        given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .post("/book/add")
                .then()
                .statusCode(Integer.parseInt(ResponseCodes._SUCCESSFUL.getCode()));
    }
    /*
    Test should pass given that during startup
    some books are inserted in the books table
     */

    @Test
    public void availableBooks()
    {
        given()
                .get("/book/get-all")
                .then()
                .statusCode(Integer.parseInt(ResponseCodes._SUCCESSFUL.getCode()));
    }

    @Test
    public void availableBooksByAuthorId()
    {
        /*
        here the author id is 1 since during startup,
        three authors are inserted with ids 1,2 and 3
         */
        given()
                .get("/book/get-author/1")
                .then()
                .statusCode(Integer.parseInt(ResponseCodes._SUCCESSFUL.getCode()));
    }

    @Test
    public void deleteBook()
    {
        /*
         Deletion occurs through a path variable isbn
         Again, during startup, a book with isbn '123' is inserted into
         books table.
         */
        given()
                .delete("/book/delete/123")
                .then()
                .statusCode(Integer.parseInt(ResponseCodes._SUCCESSFUL.getCode()));
    }

    @Test
    public void updateBook()
    {
        /*
        performs update on record on the books table where the isbn is '123'
        One such record is already inserted into the books table during startup
         */
        var requestPayload = Map.of("isbn","123","price",2500.00,"quantity",5);
        JSONObject jsonObject = new JSONObject(requestPayload);
        String jsonData = jsonObject.toString();
        given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .patch("/book/update");

        /*
        calls endpoint /book/get/{isbn} which returns bookoperationresponse base on the provided isbn
         */
        var responseMap = given()
                .get("/book/get-isbn/123")
                .then()
                .statusCode(Integer.parseInt(ResponseCodes._SUCCESSFUL.getCode()))
                .extract()
                 .jsonPath()
                .getMap("$");
        System.out.println("\n\nhere___> "+responseMap.get("status")+"\n\n");
        /*
        During startup, a book with isbn '123' having exactly 10 copies is inserted into the books table
        When more 5 books with same isbn '123' is added during the above update, the quantity available after the update should be 15
         */
        assertThat(responseMap.get("status")).isEqualTo(ResponseCodes._SUCCESSFUL.getCode());

    }
}
