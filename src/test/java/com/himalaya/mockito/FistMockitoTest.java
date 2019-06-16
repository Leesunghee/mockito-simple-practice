package com.himalaya.mockito;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FistMockitoTest {


    @Test
    public void VerificationTest() {
//        fail("Not yet implemented");

        // mock
        List mockedList = mock(List.class);

        // mock 사용하기
        mockedList.add("one");
        mockedList.clear();

        // verification
//        verify(mockedList).add("one");
        verify(mockedList).clear();

        List<String> testMock = mock(ArrayList.class);
        testMock.add("1");
        testMock.add("2");
        testMock.add("3");

        verify(testMock, atLeastOnce()).add(anyString());

        verify(testMock, atLeast(3)).add(anyString());

        verify(testMock, atMost(3)).add(anyString());

        verify(testMock, times(3)).add(anyString());

        verify(testMock, times(1)).add("1");
        verify(testMock, times(1)).add("2");
        verify(testMock, times(1)).add("3");

        verify(testMock, never()).add("4");

    }

    @Test
    public void stubbingTest() {
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));
//        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(10));

        verify(mockedList).get(0);
    }

    @Test
    public void argumentMatchersTest() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(anyInt())).thenReturn("int");
        when(mockedList.add(anyFloat())).thenReturn(true);
        when(mockedList.add(anyString())).thenReturn(true);

        System.out.println(mockedList.get(999)); // int
        System.out.println(mockedList.add(3.3)); // true
        System.out.println(mockedList.add("string")); // true

        verify(mockedList).get(anyInt());
        verify(mockedList).add(anyFloat());
        verify(mockedList).add(eq("string"));

    }

    @Test
    public void verficationInOrderTest() {

        // single mock
        List singleMock = mock(List.class);

        singleMock.add("first");
        singleMock.add("second");

        InOrder inOrder = inOrder(singleMock);

        inOrder.verify(singleMock).add("first");
        inOrder.verify(singleMock).add("second");

        // multiple mocks
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        // using mocks
        firstMock.add("first");
        secondMock.add("second");

        inOrder = inOrder(firstMock, secondMock); // pass multiple mocks to verify

        inOrder.verify(firstMock).add("first");
        inOrder.verify(secondMock).add("second");
    }


}
