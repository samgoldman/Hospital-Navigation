package service;

import model.Node;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testclassifications.FastTest;

import javax.xml.crypto.Data;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class DatabaseServiceTest {
    DatabaseService myDB;

    @Before
    public void setUp(){

        try {
            myDB = DatabaseService.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() throws Exception {
        myDB.wipeTables();
        myDB.close();
    }


    @Test
    @Category(FastTest.class)
    public void insertNode() {
        Node testNode = new Node("ACONF00102", 1580, 2538, "2", "BTM", "HALL", "Hall", "Hall");
        // make sure that the new node is successfully inserted
        assertThat(myDB.insertNode(testNode), is(true));
        // make sure that the same node cannot be inserted a second time
        assertThat(myDB.insertNode(testNode), is(false));
    }

    @Test
    @Category(FastTest.class)
    public void getNode(){
        Node testNode = new Node("ACONF00102", 1580, 2538, "2", "BTM", "HALL", "Hall", "Hall");
        myDB.insertNode(testNode);
        Node toGet = myDB.getNode("ACONF00102");
        assertThat(toGet.getNodeID(),is("ACONF00102"));
        assertThat(toGet.getXcoord(),is(1580));
        assertThat(toGet.getYcoord(),is(2538));
        assertThat(toGet.getFloor(),is("2"));
        assertThat(toGet.getBuilding(),is("BTM"));
        assertThat(toGet.getNodeType(),is("HALL"));
        assertThat(toGet.getShortName(),is("Hall"));
        assertThat(toGet.getLongName(),is("Hall"));

    }

    @Test
    @Category(FastTest.class)
    public void getNodeFail() {
        Node testNode = new Node("ACONF00102", 1580, 2538, "2", "BTM", "HALL", "Hall", "Hall");
        myDB.insertNode(testNode);
        assertThat(myDB.getNode("NOTINFIELD"), is(nullValue()));


    }

    @Test
    public void editNode() {
    }

    @Test
    public void deleteNode() {
    }


    @Test
    public void getNodes() {
    }

    @Test
    public void getEdges() {
    }

    // uh i legit don't know how to test this because everything relies on it and we can't delete
    // the tables yet
    @Test
    public void createTables() {
    }

    @Test
    @Category(FastTest.class)
    public void tableExists() {
        assertTrue(myDB.tableExists("NODE"));
        assertFalse(myDB.tableExists("NOTPRESENT"));


    }
}