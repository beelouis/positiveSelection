public class Main{
  public static void main(String[] args){
    PS ps = new PS();

    System.out.println("\nHi! This is a simulation of a Positive Selection algorithm");
    System.out.println("At this time, we will be using " + ps.getDet() + " detectors, ");
    System.out.println("each with " + ps.getVec() + " dimensions to their vector.");
    System.out.println("The system will contain " + ps.getTotal() + " cells, ");
    System.out.println("some of which will match the antigen shapes");
    System.out.println("Random detectors will be generated, and if their");
    System.out.println("(hamming distance / numberOfBitsInVector) <= 0.2");
    System.out.println("they will be added to a set of detectors\n");

    ps.generateAntigenShapes();   // generate the known antigen shapes
    ps.printAntigenShapes();      // print the known antigens 
    ps.generateCells();           // create cells
    ps.generateDetectors();       // generate detectors with random vectors, and add them to the list if they match at least 4 bits on any antigen
    ps.printDetectors();          // print the detector list, and the antigen shape that they matched against
  }
}
