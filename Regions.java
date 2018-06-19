
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;



public class Regions {

    ArrayList<Region> region_list;
    boolean sorted = false;
    

    
    public Regions( ) {
	this.region_list = new ArrayList<Region>();

    }


    public boolean readin_bedfile(String filename ){

	Pattern BED_PATTERN = Pattern.compile("^(\\w+)\\t(\\d+)\\t(\\d+)");

	ArrayList<Region> region_list = new ArrayList<Region>();

	try (Stream<String> stream = Files.lines(Paths.get( filename ))) {

	    //1. filter line 3
	    //2. convert all content to upper case
	    //3. convert it into a List
	    stream.map(String::toUpperCase)
		  .forEach( line -> { Matcher m = BED_PATTERN.matcher(line);
			  if (m.find( )) {
			      Region region = new Region( m.group(1),
							  Integer.parseInt(m.group(2)) + 1, // add one as bed is 0 indexed
							  Integer.parseInt(m.group(3)));
			      this.region_list.add( region );
				  
			  }
		    });
	    
	} catch (IOException e) {	    
	    e.printStackTrace();
	    return false;
	}

	this.sorted = false;
	return true;
    }
    
    public int size() {
	return this.region_list.size();
    }
    

    
    public void sort() {
	//Collections.sort(this.region_list, Collections.reverseOrder());
	Collections.sort(this.region_list);
	this.sorted  = true;

    }


    public void chromosomes() {
	String prevChrom = null;

	for( Region region : this.region_list ) {
	    if (prevChrom == null || ! region.chrom.equals( prevChrom )) {
		prevChrom = region.chrom;
		System.out.println(String.format("chromosome: %s", prevChrom));
	    }
	}	    
    
    }    

    public void add( Region r) {
	this.sorted = false;
	this.region_list.add( r);
    }

    
    public Region get( int index) {
	return(this.region_list.get( index ));
    }

        
    public double length() {
	return (this.region_list.stream().parallel().mapToInt(Region::length).sum());
    }
    
    
    public int length(int index) {
	return( this.region_list.get( index ).length() );
    }


    public void merge_overlapping(int flank) {

	System.out.println("merging with a flank of " + flank );

	
	if ( ! this.sorted )
	    sort();

	

	for( int i = 0; i < this.region_list.size() - 1; i++ ) {

	    if ( ! this.region_list.get( i ).chrom.equals( this.region_list.get( i + 1 ).chrom))
		continue;

	    
	    if (this.region_list.get( i ).end + flank > this.region_list.get( i +1  ).start) {

		System.out.println("Merging two regions: "+ this.region_list.get( i )+ " with "+ this.region_list.get( i + 1));

		this.region_list.get( i ).end = this.region_list.get( i + 1).end;
		this.region_list.remove( i + 1 );
		i--;
	    }
	}
    }

    public void merge_overlapping() {
	merge_overlapping( 0 );
    }

    
}
