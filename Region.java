public class Region implements Comparable<Region> {
	
    String chrom;
    int start, end;
	
	
    public Region( ) {
    }

    public Region( String chrom, int  start, int end ) {
	this.chrom = chrom;
	this.start = start;
	this.end   = end;
    }
	
    public int length() {
	return end - start  + 1 ;
    }

    public boolean equals( Region region ) {
	if ( region.chrom.equals( this.chrom) &&
	     region.start == this.start &&
	     region.end   == this.end )
	    return true;

	return false;
    }

    public int compareTo( Region region ) {


	if ( this.equals( region ))
	    return 0;
		
	if( region.chrom.compareTo(this.chrom) == -1  ||
	    region.start < this.start ||
	    region.end   < this.end )
	    return 1;
	
		     
	return -1;
    }

    public boolean equalsIgnoreCase( String region) {
	return this.toString().equalsIgnoreCase( region );
    }

    public boolean equalsChrom( String chrom) {
	return this.chrom.equalsIgnoreCase( chrom);
    }

    
    @Override
    public String toString() {
        return String.format("%s:%d-%d", this.chrom, this.start, this.end);
    }
    
}
