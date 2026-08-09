package streams;



/*
*
* H.W.
*
* flatmap
* parallel streams
*
* Streams -> pipeline of data
* flow of data
*
* to process data with minimal code and slightly more efficient (because internally streams use tree like structure)
*
* streams will not never change your original data
*
* types of streams:-
*
* 1. Intermediate
* 2. Terminal
*
*
* 1. Intermediate
*
*   a. it guves reference
*   b. limit(), filter, map
*   c. to execute them completely we need terminal
*   d. we can use multiple intermediate functions at once
*   e. intermediate functions start only after terminal is introduced
*
* 2. Terminal
*
*   a. they will return the exact expected output
*   b. count, collect, findfirst, reduce , forEach
*   c. wr cannot implement multiple terminal methods because once we use terminal method, streams are dead
*
*
*
* */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {

    public static void main(String[] args) {




        List<Integer> list = List.of(10,9,8,7,6,5,4,3,2,1);






        Stream<Integer> stream = list.stream();

        System.out.println(stream);

        System.out.println(stream.limit(3).count());


        List<Integer> evenNos = list
                .stream()
                .filter((e)-> e%2==0)
                .collect(Collectors.toList());


        System.out.println(evenNos);


        List<Integer> evenSquared = list
                .stream()
                .filter((e)->e%2==0)
                .map(x ->x*x)
                .collect(Collectors.toList());

        System.out.println(evenSquared);


        List<Integer> evenSquaredSorted = list
                .stream()
                .filter((e)->e%2==0)
                .map(x->x*x)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(evenSquaredSorted);



        Optional<Integer> first = list
                .stream()
                .filter((x)->x%2==0)
                .map(x->x*x)
                .sorted()
                .findFirst();


        System.out.println(first);


        Integer smallestNo = list
                .stream()
                .filter((x)->x%2==0)
                .map(x->x*x)
                .sorted((a,b)->b-a)
                .reduce(Integer.MAX_VALUE,(a,b)->Math.min(a,b));

        Integer largest = list
                .stream()
                .filter(x->x%2==0)
                .map(x->x*x)
                .reduce(Integer.MIN_VALUE,(a,b)->Math.max(a,b));

        System.out.println(smallestNo);

        System.out.println(largest);


        int ans = Integer.MAX_VALUE;

        for (Integer i : list) {
            ans = Math.min(ans, i);
        }
        System.out.println(ans);

//        System


//
//append vs extend
        /*
        *
        * a =  [A,B]
        * b = [1,2,"apple"]
        * b.append(a);
        *
        * [1,2,"apple",[A,B]]
        *
        * extend
        * [1,2,"apple",A,B]
        *
        *
        * jagged array
        * [
        * [a,d,f]
        * [a,b,c,d,e]
        * [a,d,f,g,h,j]
        * ]
        *
        * [a,d,f,a,b,c,d,e,a,d,f,g,h,j]
        *
        *
        * */




    }
}

