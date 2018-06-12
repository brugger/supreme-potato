
import java.util.Arrays; 
import java.util.List; 
import java.util.stream.Collectors;

public class jbtool {
    
    public static void main( String[] args ) {

	Region[] regions = new Region[3];

	
	regions[ 0 ] = new Region( "chr1", 1234, 34543);
	regions[ 1 ] = new Region( "chr1", 1234, 34543);

	Region region1 = regions[ 0 ];
	Region region3 = new Region("chr3", 234435, 345435);
	regions[ 2 ] = region3;
	Region region2 = regions[1];
	    
	region2.start = 100;
	region2.end = 10000;

	Arrays.sort(regions);

	
	for( Region r : regions) {
	    if (r == null)
		continue;
		    
	    System.out.println( "Region chrom:" + r.chrom );
	    System.out.println( "Region start:" + r.start );
	    System.out.println( "Region end:" + r.end);
	    System.out.println( "Region length:" + r.length());
	    
	}

	System.out.println( region1 );

	List<Region> regions_list  = Arrays.asList(  regions );
	//String chr1s = regions_text.stream().filter( s -> s.equalsIgnoreCase("chrom1")).collect(Collectors.joining(", "));

	System.out.println( regions_list.get(0));
	System.out.println( regions_list.get(regions_list.size() - 1));

	
	//System.out.println(chr1s);

	
     
    }
}
