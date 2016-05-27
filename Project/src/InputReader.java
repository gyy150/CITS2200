import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class InputReader {
	
	public int NumberofVertex ;
	public int[] VertexArray ;
	public int[] VertexEdgeNumber;
	public int[][] AdjacencyMatrix ;
	public LinkedList<Edge> EdgeList;
	
	public InputReader() {
		
	
		
		try (BufferedReader br = new BufferedReader(new FileReader("428333.edges")))
		{

			String sCurrentLine;
			String[] v;													//edge specified by two vertex
			int Vertex1Index;
			int Vertex2Index;
			EdgeList = new LinkedList<Edge>();  		//linked list containing all edges
			
			//*********Initializing and creating the first two node in the binary tree from the first line of text file
			sCurrentLine = br.readLine();
			v = sCurrentLine.split(" ");
			int vertex1 = Integer.parseInt(v[0]);
			BinarySearchTree VertexTree = new BinarySearchTree(vertex1);  				//creating a new binary seach tree, an tree with a node 0 at root
			Vertex1Index = BinarySearchTree.IndexCounter;
			int vertex2 = Integer.parseInt(v[1]);   
			Vertex2Index = VertexTree.addNode(vertex2);	
			EdgeList.add( new Edge( Vertex1Index , Vertex2Index ,vertex1 , vertex2 )  );
			//*************************************************************************************
			
			
			
			while ((sCurrentLine = br.readLine()) != null) {
				v = sCurrentLine.split(" ");
				System.out.println(v[0] + " " + v[1]);
				vertex1 = Integer.parseInt(v[0]);
				Vertex1Index = VertexTree.addNode(vertex1);				//if vertex has never been put into the tree brfore
				
				vertex2 = Integer.parseInt(v[1]);          
				Vertex2Index = VertexTree.addNode(vertex2);	
				EdgeList.add( new Edge( Vertex1Index , Vertex2Index ,vertex1 , vertex2 )  );
				
			}
			
			
			//************contructing the vertext array and adjacency matrix from the edge list 
			NumberofVertex = BinarySearchTree.IndexCounter+1;
			VertexArray = new int[NumberofVertex];
			VertexEdgeNumber = new int[NumberofVertex];
			AdjacencyMatrix = new int[NumberofVertex][NumberofVertex];
			ListIterator<Edge> listIterator = EdgeList.listIterator();
			while (listIterator.hasNext()) {
				Edge E = listIterator.next();
				
				//*******************if the number of edges incident on one node is 0, then this node has never been seen before, so update vertex array first
				if(VertexEdgeNumber[E.Vertex1Index] == 0 ){
					VertexArray[E.Vertex1Index] = E.Vertex1;
					VertexEdgeNumber[E.Vertex1Index] = 1; 
				}
				/***
				else{
					VertexEdgeNumber[E.Vertex1Index] = VertexEdgeNumber[E.Vertex1Index] + 1; 					//if not, then only update unmber of incident edges
				}
				***/
				if(VertexEdgeNumber[E.Vertex2Index] == 0 ){
					VertexArray[E.Vertex2Index] = E.Vertex2;
					VertexEdgeNumber[E.Vertex2Index] = 1; 
				}
				/***
				else{
					VertexEdgeNumber[E.Vertex2Index] = VertexEdgeNumber[E.Vertex2Index] + 1; 
				}
				***/
				//*************************************************************************************************
				
				AdjacencyMatrix[E.Vertex1Index][E.Vertex2Index] = 1;
				AdjacencyMatrix[E.Vertex2Index][E.Vertex1Index] = 1;          //since the graph is undirected, then update the symmetrical value in the matrix
		    }
			//******************************************************************************************************
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
    public void PrintAdjacencyMatrix(){
    	for(int i = 0; i < NumberofVertex; i++){
		      for(int j = 0; j < NumberofVertex; j++){
		         System.out.printf("%5d ", AdjacencyMatrix[i][j]);
		      }
		      System.out.println();
		}
    }
	
	
	public int FindDegreeCentrality(){
		
		int MaxNumberOfConnection = 0;
		int v = 0;
	    for (int i=0; i < NumberofVertex; i++) {
	    	int NumberofConnection = 0;
	    	for (int j=0; j < NumberofVertex; j++) {
	    		if (AdjacencyMatrix[i][j] >0 ) {
	    			NumberofConnection++;
	    		}
	    	}
	    	VertexEdgeNumber[i] = NumberofConnection;
	        if(MaxNumberOfConnection < NumberofConnection ){
	        	MaxNumberOfConnection = NumberofConnection;
	        	v = i;
	        }
	    }
	    return VertexEdgeNumber[v];
	}
	
	public float FindClosenessCentrality(int SourceVertexIndex){
		
		int[] distance = new int[NumberofVertex];
		
		Arrays.fill(distance, Integer.MAX_VALUE);     	//innitialize the array with infinite distance
		distance[SourceVertexIndex] = 0;				//set the distance of source to be 0
		
		//********************bell-man ford search, *************************************************************************//
		for(int i =0; i<NumberofVertex-1; i++){
			for( Edge E : EdgeList   ){												//each edge represent a egde going from vertex 1 to vertex 2
				
				System.out.println("" );
				System.out.println(E.Vertex1Index );
				System.out.println(E.Vertex2Index);
				System.out.println(distance[E.Vertex1Index] );
				System.out.println(distance[E.Vertex2Index] );
				System.out.println("hellop" );
				
				int Weight = AdjacencyMatrix[E.Vertex1Index][E.Vertex2Index];         //the weight of egde from vertex1 to vertex 2
				if( Weight ==0 ){													//if the weight is zero, which means it was not initialized in the adjacency matrix, then vertex1 cannot be reached from vertex1, meanning the actually distance is infinite
					Weight = Integer.MAX_VALUE;
				}	
				
				if(   distance[E.Vertex1Index]!= Integer.MAX_VALUE &&  Weight!= Integer.MAX_VALUE  &&  distance[E.Vertex2Index] >  distance[E.Vertex1Index] + Weight      ){
					  distance[E.Vertex2Index] = distance[E.Vertex1Index] + Weight;   //AdjacencyMatrix[E.Vertex2Index][E.Vertex1Index]   distance from vertex2 to vertex 1
				}
				if(   distance[E.Vertex2Index]!= Integer.MAX_VALUE &&  Weight!= Integer.MAX_VALUE  &&  distance[E.Vertex1Index] >  distance[E.Vertex2Index] + Weight      ){
					  distance[E.Vertex1Index] = distance[E.Vertex2Index] + Weight;   //AdjacencyMatrix[E.Vertex2Index][E.Vertex1Index]   distance from vertex2 to vertex 1
				}
			}
		}
		
		int c = 0;
		System.out.println("****************distance array for source vertex********************************" );
		for(int i = 0; i < NumberofVertex; i++){
			 System.out.printf("%5d ",distance[i] );
			 if(distance[i] == Integer.MAX_VALUE ){
				 return 0;								//fairness is infinity, so close centrality is 0 
			 }
			 c = c+ distance[i];
		}
		System.out.println();
		System.out.println("****************distance array for source vertex********************************");
		
		return c;
	}
	
}
