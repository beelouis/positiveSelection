import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;
import java.util.Random;

public class PS{

  private int nTotal;                                 // number of cells in the system
  private int nDet;                                   // number of total detectors
  private int nVec;                                   // number of bits in vector
  private int numAntigens;                            // number of known antigen shapes to look for
  private double threshold;                           // value to determine if the detector matches the antigen
  private ArrayList<Agent> allCells;                  // collection of all cells, including antigens
  private ArrayList<Agent> detectors;                 // collection of nDet detector cells
  private ArrayList<Vector<Integer>> antigenShapes;   // collection of vectors describing known antigen shapes

  public PS(){
      nTotal = 100;
      nDet = 15;
      nVec = 15;
      numAntigens = 2;
      threshold = 0.2;
      allCells =  new ArrayList<>();
      detectors = new ArrayList<>();
      antigenShapes = new ArrayList<>();
  }

  // =================================================================
  // ============== getters to print in main file ====================
  // =================================================================
  public int getTotal(){  return nTotal;      }
  public int getDet(){    return nDet;        }
  public int getVec(){    return nVec;        }
  public int getNumAnt(){ return numAntigens; }

  // =================================================================
  // ============== generate and print antigens ======================
  // =================================================================

  public void generateAntigenShapes(){
    // add random 0's and 1's into antigen shape vector
    for (int i = 0; i < numAntigens; i++){
        Vector<Integer> vector = new Vector<>();
        Random ran = new Random();
        for (int j = 0; j < nVec; j++) {
            vector.add(ran.nextInt(2));
        }
        antigenShapes.add(vector);
    }
  }

  public void printAntigenShapes(){
    System.out.println("Antigen Shapes:");
    for (Vector<Integer> v : antigenShapes) System.out.println(v);
    System.out.println("");
  }

  // =================================================================
  // ===================== generate all cells ========================
  // =================================================================

  public void generateCells(){
    System.out.println("Generating " + nTotal + " cells...\n");
    while (allCells.size() < nTotal){
      allCells.add(new Agent(nVec));
    }
  }

  // =================================================================
  // ============== generate and print detectors======================
  // =================================================================

  public void generateDetectors(){
    int count = 0;
    while (detectors.size() < nDet){
      Agent detector = new Agent(nVec);
      count++;
      for (Vector<Integer> shape : antigenShapes){
          if (affinity(detector, shape)){
            detectors.add(detector);
            break;
          }
      }
    }
    System.out.println("Generated " + count + " detectors to get " + nDet + " suitable ones\n");
  }

  public boolean affinity(Agent detector, Vector<Integer> shape){
    // returns true when the detector matches the shape of the antigen, else false
      Vector<Integer> hammingVector = new Vector<>();
      for (int i = 0; i < nVec; i++){
        // Iterate along the vector, and set the hamming vector to 1 when theres a difference in bits
          if (detector.getVector().get(i) == shape.get(i)){
            hammingVector.add(0);
          } else {
            hammingVector.add(1);
          }
      }
      // count the hamming distance, and scale it to the size of the vector
      double distance = Collections.frequency(hammingVector, 1);
      double thresholdValue = distance / nVec;
      if (thresholdValue <= threshold){
        // these are all for debugging and displaying system status; not the algorithm itself
        detector.addMatch(shape);
        detector.addHammingVector(hammingVector);
        detector.addDistance(distance);
        detector.addThresholdValue(thresholdValue);
        return true;
      }
      else return false;
  }

  public void printDetectors(){
    if (detectors.size() == 0) System.out.println("Empty list!");
    else {
      System.out.println("Printing detectors: \n");
      for (Agent detector : detectors){
        System.out.println("Detector Shape: " + detector.getVector());
        System.out.println("Antigen Match:  " + detector.getMatch());
        System.out.println("Hamming Vector: " + detector.getHammingVector());
        System.out.println("Hamming Distance: " + detector.getDistance());
        System.out.println("Threshold Value: " + detector.getThresholdValue() + "\n");
      }
      System.out.println("\n");
    }
  }

}
