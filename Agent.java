import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


// These are essentially the superclass that I would use to generate the child class of Detectors and Antigens
// but i dont know how to do actual inheritance. so all cells are just Agents
public class Agent{

  private Vector<Integer> vector;
  private Vector<Integer> matchAntigen;
  private Vector<Integer> hammingVector;
  private double distance;
  private double thresholdValue;
  private boolean isAntigen;

  public Agent(int nVec){
    // generate a random vector of parameterized size
    isAntigen = false;
    vector = new Vector<>();
    Random ran = new Random();
    for (int i = 0; i < nVec; i++) vector.add(ran.nextInt(2));
  }

  public void addMatch(Vector<Integer> match){
    matchAntigen = match;
  }

  public void addHammingVector(Vector<Integer> h){
    hammingVector = h;
  }

  public void addDistance(double i){
    distance = i;
  }

  public void addThresholdValue(double t){
    thresholdValue = t;
  }

  public void markAntigen(){
    isAntigen = true;
  }

  public Vector getMatch(){
    return matchAntigen;
  }

  public Vector getHammingVector(){
    return hammingVector;
  }

  public double getDistance(){
    return distance;
  }

  public double getThresholdValue(){
    return thresholdValue;
  }

  public Vector getVector(){
    return vector;
  }

}
