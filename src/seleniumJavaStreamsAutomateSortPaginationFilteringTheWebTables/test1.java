package seleniumJavaStreamsAutomateSortPaginationFilteringTheWebTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;


public class test1 {
	//Count the number of name starting with A in List
	//@Test
	public void regular() {
	ArrayList<String> names = new ArrayList<String>();
	names.add("Alan");
	names.add("Don");
	names.add("Alex");
	names.add("Adam");
	names.add("Ram");
	int count=0;
	
	for(int i = 0; i<names.size();i++) 
	{
		String actual = names.get(i);
		
		if(actual.startsWith("A"))
				{
					count++;
				}
	}
	System.out.println(count);
	}
	
	//Count the number of name starting with A in List using Java Streams
	@Test
	public void streamFilter() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Alan");
		names.add("Don");
		names.add("Alex");
		names.add("Adam");
		names.add("Ram");
		
		
		//There is no intermediate operation if there is no terminal operation
		//Terminal operation will execute only if intermediate operation (filter) returns true
		//We can create 
		//How to use filter in Stream API
		long c = names.stream().filter(s->s.startsWith("A")).count();
		System.out.println(c);
		
		long d = Stream.of("Alan", "Adam", "Ben", "Arlon").filter(s->
		{
			s.startsWith("A");
			return false;
		}).count();
		System.out.println(d);
		
		//Print all names of the arraylist 
		names.stream().filter(s->s.length()>4).forEach(s->System.out.println(s));
		//Print 1st name of the arraylist
		names.stream().filter(s->s.length()>4).limit(1).forEach(s->System.out.println(s));
	}
	
	public void streamMap() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Joe");
		names.add("Doc");
		names.add("Raman");
		
		//Print names which have last letter as "a" displaying them in Uppercase
		//Alana-ALANA
		Stream.of("Alana", "Adam", "Bena", "Arlon", "Jorgea").filter(s->s.endsWith("a")).map(s->s.toUpperCase())
		.forEach(s->System.out.println(s));
		
		//Print names which have first letter as "a" displaying them in Uppercase and sorted
		List <String> names1 = Arrays.asList("Alana", "Adam", "Bena", "Arlon", "Jorgea");
		names1.stream().filter(s->s.startsWith("A")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		
		//Merging 2 different streams
		Stream<String> newStream = Stream.concat(names.stream(), names1.stream());
		//newStream.sorted().forEach(s->System.out.println(s));
		boolean flag = newStream.anyMatch(s->s.equalsIgnoreCase("Adam"));
		Assert.assertTrue(flag);
		
	}
	
	public void streamCollect() {
		List<String> ls =  Stream.of("Alana", "Adam", "Bena", "Arlon", "Jorgea").filter(s->s.endsWith("a")).map(s->s.toUpperCase())
		.collect(Collectors.toList());
		System.out.println(ls.get(0));
		
		//
		List <Integer> values = Arrays.asList(3,2,2,7,5,1,9,7);
		//Print unique numbers from this array
		//Sort the array - 3rd index - 1,2,3,5,7,9
		values.stream().distinct().forEach(s->System.out.println(s));
		List <Integer> li = values.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(li.get(2));
	}
}
