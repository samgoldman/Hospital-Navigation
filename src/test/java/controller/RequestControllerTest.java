package controller;

import model.request.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

public class RequestControllerTest {


    RequestController reqCrl1 ;
    RequestController reqCrl2 ;
    Request newReq ;

//    RequestController mockRequestController = spy(new RequestController());

    @Before
    public void mockMethod() {
//        when(mockRequestController.initialize()).thenReturn();
    }

    @Before
    public void setUp() throws Exception {

    }


    // test showHome()
    @Test
    @Category(FastTest.class)
    public void showHomeTest () {
        // test if switching screens
        // will do research, UI help?!
    }

    // test makeRequest()
    @Test
    @Category(FastTest.class)
    public void makeRequestTest () {
//        reqCrl1.makeRequest(newReq);
//        assertTrue(reqCrl1.getPendingRequests().contains(newReq));
    }


    // test fufillRequest ()
    @Test
    @Category(FastTest.class)
    public void fufillRequestTest () {
//        reqCrl2.getPendingRequests().add(newReq) ;
//        reqCrl2.fufillRequest("ID", "Someone");
//        assertFalse(reqCrl2.getPendingRequests().contains(newReq));
    }

    @After
    public void tearDown() throws Exception { }
}
