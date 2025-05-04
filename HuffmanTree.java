
import java.util.*;

   class HuffmanTree implements Comparable<HuffmanTree>{
    
      BinaryNode<Integer> root;
      int weight; // computed from the frequencies in each merge.
   
      public HuffmanTree(Integer a, int freq){
         root = new BinaryNode(a);
         weight = freq; 
      }
    
      public HuffmanTree(HuffmanTree t1, HuffmanTree t2)
      {
         weight = t1.weight+t2.weight;
         root   = new BinaryNode(null, t1.root, t2.root);
      }
    
   
      public int compareTo(HuffmanTree other)
      {
         return weight-other.weight;
      }
   
      
      public void codes(Map<Integer, String> allCodes)
      {
          codes(root, "", allCodes);
      }
     
      public void codes(BinaryNode n, String s, Map<Integer, String> allCodes)
      {
          if (n.element == null)
          {
              codes(n.left,s+"0", allCodes);
              codes(n.right,s+"1", allCodes);
          }
          else
          {
              allCodes.put((Integer)n.element, s);
          }
      }
       
      
      public void codes(String[] c){
      
         codes(root,"",c);
      }
   
   
      private void codes (BinaryNode n, String s, String[] theCodes){
         if (n.element ==null)
         { 
            codes(n.left,s+"0",theCodes);
            codes(n.right,s+"1",theCodes);
         }
         else
            theCodes[((Integer)(n.element)).intValue()] = s;
      }
      
   
   }
