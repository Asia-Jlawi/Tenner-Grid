import java.util.* ; 
import java.util.Arrays ;

public class AI{

   public static void main (String[]args){
     
        
      int[][] a = { { -1, 6, 2, 0, -1, -1, -1, 8, 5, 7},
                      { -1, 0, 1, 7, 8, -1, -1, -1, 9, -1},
                      { -1, 4, -1, -1, 2, -1, 3, 7, -1, 8},
                      { 13, 10, 8, 7, 19, 16, 11, 19, 15, 17} };
                      
      /*int[][] a = { { -1, 9, 4,-1, -1, 7, -1, 3, -1, -1},
                      { 4, 8, -1, 2, -1, 1, -1, 7, 0, 3},
                      { -1, 9, 4, -1, -1, -1, 3, 2, 1, -1},
                      { 13, 26, 14, 2, 20, 14, 16, 12, 6, 12} };*/
                      
      /*int[][] a = { { 3, -1, 6, 1, -1, 5, 8, 7, 9, 2},
                      { -1, -1, -1, 3, -1, -1, -1, 4, -1, -1},
                      { 0, 1, -1, 4, -1, 7, 9, 8, -1, 2},
                      { 11, 7, 18, 8, 15, 13, 23, 19, 12, 9} };*/
                      
      /*int[][] a = { { 1, 3, 4, -1, -1, -1, -1, 5, 7, 0},
                      { -1, -1, 9, -1, -1, -1,-1 , 8, 4, 2},
                      { -1, 8, -1, 6, 2, 9, -1, -1, -1, -1},
                      { 7, 17, 16, 9, 17, 15, 16,13,16,9}};*/
                      
      /*int[][] a = { { -1, 6, 2, 0, -1, -1, -1, 8, 5, 7},
                      { -1, 0, 1, 7, 8, -1, -1, -1, 9, -1},
                      { -1, 4, -1, -1, 2, -1, 3, 7, -1, 6},
                      { 13, 10, 8, 7, 19, 16, 11, 19, 15, 17} };*/              
                                      
      int consistency = 0;                
      LinkedList[] linkedArray  = new LinkedList[10] ;
      for(int LI = 0 ; LI <10 ; LI++)
      {
         linkedArray[LI] = new LinkedList<int[]>() ;
      }
      
      int x,y,z;   
                      
      for(int c = 0 ; c<=9 ; c++){//for each culomn
         for(int i = 0 ; i<=9 ; i++)//for numbers
            for(int j = 0 ; j<=9 ; j++)
               for(int k = 0 ; k<=9 ; k++){
                           
                  if (a[0][c] != -1)
                     x= a[0][c];
                  else
                     x= i;
                                
                  if (a[1][c] != -1)
                     y= a[1][c];
                  else
                     y= j;
                                
                  if (a[2][c] != -1)
                     z= a[2][c];
                  else
                     z= k;
                     
                  if(x+y+z == a[3][c]){
                        consistency++;
                        boolean bol = true ;
                     
                        for(int p = 0 ; p<=9 ; p++)
                        {
                        consistency++;
                        if(c != p ) 
                           if(a[0][p] == x)
                              bol = false ; 
                        }
                        for(int p = 0 ; p<=9 ; p++)
                        {
                        consistency++;
                        if(c != p ) 
                           if(a[1][p] == y)
                              bol = false ; 
                        }
                        for(int p = 0 ; p<=9 ; p++)
                        {
                        consistency++;
                        if(c != p ) 
                           if(a[2][p] == z)
                              bol = false ; 
                        }
                     
                        if(x==y||y==z)
                            bol = false ;
                            
                        consistency++;
                     
                        if(c-1>=0)
                            if(x == a[1][c-1] || z == a[1][c-1] || y == a[0][c-1] || y == a[2][c-1])
                                bol = false ;
                                
                        consistency++;
                        
                        if(c+1<10)
                            if(x == a[1][c+1] || z == a[1][c+1] || y == a[0][c+1] || y == a[2][c+1])
                                bol = false ; 
                                
                        consistency++;     
                     
                     
                        
                        int[] pA = {x,y,z} ;
                  
                        if (bol){ 
                            if (linkedArray[c].isEmpty())// check if list is not empty
                                linkedArray[c].add(pA) ;
                            else{
                                boolean bol2 = true ; 
                                Iterator it = linkedArray[c].iterator();
                           
                                while (it.hasNext()){
                                    consistency++;
                                    int[] temp = (int[])it.next();
                                    if(Arrays.equals(pA,temp))
                                        bol2 = false ; 
                                }
                           
                                if (bol2){
                                    linkedArray[c].add(pA) ;
                                }
                            }
                        }
                     
                    }
                }
        }
        
        System.out.println("initial state(-1 means the cell is empty):");
        for (int i = 0; i<4; i++) {
                for (int j = 0; j<10; j++) {
                    System.out.printf("%2d    ", a[i][j]);
                }
                System.out.println();
        }
        
        System.out.println();
      
      
        int[][] copyA = copyArray(a);
         
      
        LinkedList[] linkedArrayCOPY = copyArrList(linkedArray);
         
        
        
        System.out.println("Simple backtracking: ");
        long start1 = System.nanoTime();
        int[][] result1 = Backtracking( copyA , linkedArrayCOPY);
        long end1 = System.nanoTime();
        if(result1 != null){
        System.out.print("final CSP Tenner variable assignments: ");
        for (int i = 0; i<10; i++) {
            System.out.print("c"+i+" = ");
            for (int j = 0; j<3; j++) {
                System.out.print(result1[j][i]+"  ");
            }
        }
        System.out.println();}
        System.out.println("number of variable assignments: "+numOfVarAssignmentsBacktracking);
        System.out.println("number of consistency checks: "+ consistency);
        System.out.println("time used to solve the problem in nanoseconds: "+ (end1-start1));
        System.out.println("the solution: ");
        if(result1 == null)
        System.out.println("there is no solution");
        else{
            for (int i = 0; i<4; i++) {
                for (int j = 0; j<10; j++) {
                    System.out.printf("%2d    ",result1[i][j]);
                }
                System.out.println();
            }
        }
        System.out.println();
        
        System.out.println("Forward checking: ");
        long start2 = System.nanoTime();
        int[][] result2 = FC( copyA , linkedArrayCOPY);
        long end2 = System.nanoTime();
        if(result1 != null){
        System.out.print("final CSP Tenner variable assignments: ");
        for (int i = 0; i<10; i++) {
            System.out.print("c"+i+" = ");
            for (int j = 0; j<3; j++) {
                System.out.print(result2[j][i]+"  ");
            }
        }
        System.out.println();}
        System.out.println("number of variable assignments: "+numOfVarAssignmentsFC);
        System.out.println("number of consistency checks: "+ consistency);
        System.out.println("time used to solve the problem in nanoseconds: "+(end2-start2));
        System.out.println("the solution: ");
        if(result2 == null)
            System.out.println("there is no solution");
        else{
            for (int i = 0; i<4; i++) {
                for (int j = 0; j<10; j++) {
                    System.out.printf("%2d    ",result2[i][j]);
                }
                System.out.println();
            }
        }
        System.out.println();
        
        //////

        System.out.println("Forward checking with the MRV heuristic: ");
        long start3 = System.nanoTime();
        int[][] result3 = MRV(copyA , linkedArrayCOPY );
        long end3 = System.nanoTime();
        if(result1 != null){
        System.out.print("final CSP Tenner variable assignments: ");
        for (int i = 0; i<10; i++) {
            System.out.print("c"+i+" = ");
            for (int j = 0; j<3; j++) {
                System.out.print(result3[j][i]+"  ");
            }
        }
        System.out.println();}
        System.out.println("number of variable assignments: "+numOfVarAssignmentsMRV);
        System.out.println("number of consistency checks: "+ consistency);
        System.out.println("time used to solve the problem in nanoseconds: "+(end3-start3));
        System.out.println("the solution: ");
        if(result3 == null)
            System.out.println("there is no solution");
     
        else{
            for (int i = 0; i<4; i++) {
                for (int j = 0; j<10; j++) {
                    System.out.printf("%2d    ",result3[i][j]);
                }
                System.out.println();
            }
        }
        
        
    }
    
    static int[][] MRV(int[][] ca, LinkedList[] cLinkArray) {
    if (numOfcolumns == 10)
        return ca;

    // Finding the variable with the minimum remaining values
    int min = 1000;
    int minc = -1;
    for (int i = 0; i < 10; i++) {
        boolean bol4 = true;
        for (int j = 0; j < numOfcolumns; j++) {
            if (cnum[j] == i)
                bol4 = false;
        }
        if (bol4) {
            if (cLinkArray[i].size() < min) {
                min = cLinkArray[i].size();
                minc = i;
            }
        }
    }

    // MRV heuristic selects the column `minc`
    cnum[numOfcolumns] = minc;
    
    // Loop through each value in the domain of `minc`
    for (int i = 0; i < cLinkArray[minc].size(); i++) {
        int[][] cac = copyArray(ca);
        LinkedList[] vc = copyArrList(cLinkArray);
        int[] value = (int[]) cLinkArray[minc].get(i);

        // Assign the value to the variable
        for (int j = 0; j < 3; j++)
            cac[j][minc] = value[j];

        numOfVarAssignmentsMRV++;

        // Forward checking: update remaining variables' domains
        for (int k = 0; k < 10; k++) {
            if (k != minc) {
                for (int w = 0; w < vc[k].size(); w++) {
                    int[] chv = (int[]) vc[k].get(w);
                    for (int h = 0; h < 3; h++)
                        if (value[h] == chv[h]) {
                            vc[k].remove(w);
                            w--;
                            break;
                        }
                }
            }
        }


        numOfcolumns++;
        int[][] test = MRV(cac, vc);
        numOfcolumns--;
        if (test != null)
            return test;
    }
    return null;
}

   
   
   static int numOfVarAssignmentsBacktracking = 0; 
    
   static int coloumnc = 0 ;
    
   static int[][] Backtracking(int[][] ca , LinkedList[] cLinkArray ){
   
        if(coloumnc == 10) //base case
        return ca ;
   
   
        for(int i = 0 ; i < cLinkArray[coloumnc].size() ; i++){
   
   
            int[][] cac = copyArray(ca);
            LinkedList[] vc = copyArrList(cLinkArray);
   
            int[] value = (int[])cLinkArray[coloumnc].get(i);
   
            for(int j = 0 ; j < 3 ; j++)
                cac[j][coloumnc] = value[j];
                
            numOfVarAssignmentsBacktracking++;    
   
            for(int k = 0 ; k < 10  ; k++){
                if(k!=coloumnc){
                    for(int w = 0 ; w < vc[k].size() ; w++){
                        int[] chv = (int[])vc[k].get(w);
                        for(int h = 0 ; h < 3 ; h++)
                            if(value[h] == chv[h]){
                                vc[k].remove(w);
                                w--;
                                break;
                            }
                    }
                }
            }

   
            if(coloumnc-1>=0){
                for(int w = 0 ; w < vc[coloumnc-1].size() ; w++){
                    int[] chv = (int[])vc[coloumnc-1].get(w);
                    if(chv[0]==value[1] || chv[2]==value[1] || chv[1]==value[0] || chv[1]==value[2] ){
                        vc[coloumnc-1].remove(w);
                        w--;
                    }
                }
            }
   
            if(coloumnc+1<10){
                for(int w = 0 ; w < vc[coloumnc+1].size() ; w++){
                    int[] chv = (int[])vc[coloumnc+1].get(w);
                    if(chv[0]==value[1] || chv[2]==value[1] || chv[1]==value[0] || chv[1]==value[2] ){
                        vc[coloumnc+1].remove(w);
                        w--;
                    }
                }
            }

            coloumnc++;
   
            int[][]  test = Backtracking(cac,vc);
   
            coloumnc--;
   
            if(test != null)
                return test;
        }
        return null;
   }
   
   static int numOfVarAssignmentsFC = 0;
   
   
   static int[][] FC(int[][] ca , LinkedList[] cLinkArray ){
   
        if(coloumnc == 10) //base case
        return ca ;
   
   
        for(int i = 0 ; i < cLinkArray[coloumnc].size() ; i++){
   
   
            int[][] cac = copyArray(ca);
            LinkedList[] vc = copyArrList(cLinkArray);
   
            int[] value = (int[])cLinkArray[coloumnc].get(i);
   
            for(int j = 0 ; j < 3 ; j++)
                cac[j][coloumnc] = value[j];
            
            numOfVarAssignmentsFC++;
   
            for(int k = 0 ; k < 10  ; k++){
                if(k!=coloumnc){
                    for(int w = 0 ; w < vc[k].size() ; w++){
                        int[] chv = (int[])vc[k].get(w);
                        for(int h = 0 ; h < 3 ; h++)
                            if(value[h] == chv[h]){
                                vc[k].remove(w);
                                w--;
                                break;
                            }
                    }
                }
            }
   
            if(coloumnc-1>=0){
                for(int w = 0 ; w < vc[coloumnc-1].size() ; w++){
                    int[] chv = (int[])vc[coloumnc-1].get(w);
                    if(chv[0]==value[1] || chv[2]==value[1] || chv[1]==value[0] || chv[1]==value[2] ){
                        vc[coloumnc-1].remove(w);
                        w--;
                    }
                }
            }
   
            if(coloumnc+1<10){
                for(int w = 0 ; w < vc[coloumnc+1].size() ; w++){
                    int[] chv = (int[])vc[coloumnc+1].get(w);
                    if(chv[0]==value[1] || chv[2]==value[1] || chv[1]==value[0] || chv[1]==value[2] ){
                        vc[coloumnc+1].remove(w);
                        w--;
                    }
                }
            }
   
   
            boolean bol3=false;
   
            for(int check=0 ; check<10 ; check++){
                if(vc[check].size()==0){
                    bol3=true;
                    break;
                }
            }
   
            if(bol3)
                continue;

            coloumnc++;
   
            int[][]  test = FC(cac,vc);
   
            coloumnc--;
   
            if(test != null)
                return test;
        }
        return null;
    }
    
   static int numOfVarAssignmentsMRV = 0;

   static int numOfcolumns = 0;
   static int [] cnum = new int[10];
    
   
   
   
   static int[][] copyArray(int[][] arr){
        int[][] carr = new int[4][10];
        for(int i = 0 ; i<4 ; i++)
            for(int j = 0 ; j<10 ; j++)
                carr[i][j]=arr[i][j];
        return carr;
    }

   static LinkedList[] copyArrList(LinkedList[] l){
        LinkedList[] cl = new LinkedList[10];
        for(int m = 0 ; m <10 ; m++){
            cl[m] = new LinkedList<int[]>();}
        for(int k = 0 ; k < 10 ; k++){
            for(int w = 0 ; w < l[k].size() ; w++){
                int[] cv = (int[])l[k].get(w);
                cl[k].add(cv);
            }
        }
        return cl;
    }

    
   
 
}