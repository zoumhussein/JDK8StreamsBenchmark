/**
 * 
 */
package fr.zoumanatraore.benchmark.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Zoumana TRAORE
 * @version 20160807
 * @email public at zoumanatraore dot fr
 *
 */
public class Jdk8StreamsBenchmark {

	public static void main(String[] args){
		
		List<Integer> tab = new ArrayList<Integer>();
		int number = 10000000; //10M
		for(int i =0; i < number ; i++){
			tab.add(new Random().nextInt(Integer.MAX_VALUE));
		}
		
		System.out.println("******** sort OLD School JDK 1.7 *******");
		List<Integer> result1 = classicalSort(tab);
		
		System.out.println("******** sort STREAM NOT collected JDK 1.8 *******");
		streamSortNoCollector(tab);

		System.out.println("******** sort STREAM collected JDK 1.8 *******");
		List<Integer> result2 = streamSort(tab);
		
		System.out.println("******** sort PARALLEL STREAM NOT collected JDK 1.8 *******");		
		parallelStreamSortNoCollector(tab);
		
		System.out.println("******** sort PARALLEL STREAM collected JDK 1.8 *******");		
		List<Integer> result3 = parallelStreamSort(tab);
		
		System.out.println("******** MAX OLD School JDK 1.7 *******");
		System.out.println("duration : already computed");
		System.out.println(result1.get(result1.size() - 1));

		System.out.println("******** MAX PARALLEL STREAM collected JDK 1.8 *******");
		System.out.println(parallelStreamMax(tab));
		
		System.out.println("******** MAX STREAM collected JDK 1.8 *******");
		System.out.println(streamMax(tab));
		
		assert(result1.equals(result2));
		assert(result2.equals(result3));
		
	}

	private static List<Integer> classicalSort(List<Integer> tab) {
		Comparator<Integer> asc = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		
		long begin = System.currentTimeMillis();
		tab.sort(asc);
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
		return tab;
	}

	//************************* steam WITHOUT COLLECTOR *****************************
	
	private static void streamSortNoCollector(List<Integer> tab) {
		Comparator<Integer> asc = (i1, i2) -> i1.compareTo(i2);
		long begin = System.currentTimeMillis();
		tab.stream().sorted(asc);
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
	}
	
	private static void parallelStreamSortNoCollector(List<Integer> tab) {
		Comparator<Integer> asc = (i1, i2) -> i1.compareTo(i2);
		long begin = System.currentTimeMillis();
		tab.parallelStream().sorted(asc);
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
	}
	
	//************************* steam WITH COLLECTOR *****************************
	
	private static List<Integer> streamSort(List<Integer> tab) {
		Comparator<Integer> asc = (i1, i2) -> i1.compareTo(i2);
		long begin = System.currentTimeMillis();
		List<Integer> result = tab.stream().sorted(asc).collect(Collectors.toList());
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
		return result;
	}
	
	private static List<Integer> parallelStreamSort(List<Integer> tab) {
		Comparator<Integer> asc = (i1, i2) -> i1.compareTo(i2);
		long begin = System.currentTimeMillis();
		List<Integer> result = tab.parallelStream().sorted(asc).collect(Collectors.toList());
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
		return result;
	}
	
	//************************* MAX FINDERS *****************************
	
	private static Integer parallelStreamMax(List<Integer> tab) {
		Comparator<Integer> asc = (i1, i2) -> i1.compareTo(i2);
		long begin = System.currentTimeMillis();
		Integer result = tab.parallelStream().max(asc).get();
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
		return result;
	}
	
	private static Integer streamMax(List<Integer> tab) {
		Comparator<Integer> asc = (i1, i2) -> i1.compareTo(i2);
		long begin = System.currentTimeMillis();
		Integer result = tab.stream().max(asc).get();
		long end = System.currentTimeMillis();
		System.out.println("duration :"+(end-begin));
		return result;
	}
	

}
