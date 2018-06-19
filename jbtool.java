import java.util.*;


import java.io.*;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class jbtool {  
    

    public static void main( String[] args ) {

	Regions regions = new Regions();

	regions.readin_bedfile("refseq_nirvana_203.bed");

	regions.sort();
	
	System.out.println( regions.get(100));
	System.out.println( regions.get(101));

	System.out.println( regions.length(100));

	regions.chromosomes();
	
	//System.out.println("Nr of regions: " + regions.size());
	//regions.merge_overlapping(500000);
	System.out.println("Nr of regions: " + regions.size());

	//Region r = new Region( "chr1", 1234, 34543);
	
	//System.out.println("Chr compare:: " + r.compare_chroms("1", "11"));
	//System.out.println("Chr compare:: " + r.compare_chroms("X", "1"));


	
    }
				      
    
    public static void main_old( String[] args ) {
	
	Region[] regions = new Region[3];

	ArrayList<Region> region_list = new ArrayList<Region>();

	
	regions[ 0 ] = new Region( "chr1", 1234, 34543);
	regions[ 1 ] = new Region( "chr1", 1234, 34543);

	Region region1 = regions[ 0 ];
	Region region3 = new Region("chr3", 234435, 345400);
	regions[ 2 ] = region3;
	Region region2 = regions[1];
	    
	region2.start = 100;
	region2.end = 10000;

	Arrays.sort(regions);

	region_list.add( regions[0] );
	region_list.add( regions[1] );
	region_list.add( regions[2] );

	Collections.sort(region_list);
		
	
	for( Region r : region_list) {

	    r.start += 10;

	    System.out.println( "Region: " + r );
	    
	}

	System.out.println( region_list.get(0));
	System.out.println( "Nr of regions: " + region_list.size() );

	//	List region_chr1s =
	System.out.println( "------------------" );

	ArrayList<Region> chr1_regions = region_list.stream()
	                                       .filter( s -> "chr1".equals(s.chrom))
	                                       .collect(Collectors.toCollection(ArrayList::new));
	//.collect( Collectors.toList());
 
	
	// forEach, can, mutate each object, map transforms object to new type
	region_list.stream().filter( s -> "chr3".equals(s.chrom))
	                    .forEach( s-> s.end += 33);

	
	String chr1s = region_list.stream()
                 	          .parallel()
	                          .filter( s -> "chr3".equals(s.chrom))
	                          .map( s-> s.toString())
	                          .collect(Collectors.joining( ", "));
	


	System.out.println( chr1s );
	
	
	//String chr1_regions = region_list.stream().filter( s -> "chr1".equals(s.chrom)).collect(Collectors.toList());

	System.out.println( "------------------" );
	System.out.println( String.format("Nr of hits: %d", chr1_regions.size()));
	
	for ( Region r: chr1_regions ) {
	    System.out.println( r );
	}
	
	//System.out.println(chr1s);
    }
}
