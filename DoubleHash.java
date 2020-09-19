public class DoubleHash {
	// what members do we need
	int size;
	int numOfKeys;
	int[] hashTable;
	
	
	
	public DoubleHash() {
		this.numOfKeys = 0;
		this.size = 11;
		this.hashTable = new int[this.size];
		for(int i = 0; i < this.size; i++) {
			this.hashTable[i] = -1;
		}
//		System.out.println("Constructor");
	}
	
	//hash 1
	public int hash1(int key) {
		return key % this.size;
	}
	
	
	//hash 2
	public int hash2(int key) {
		// it must not be zero
		// we want x to be prime
		int x = 7;
		return x - (key % x);
	}
	
	
	public void insert(int key) {
		
		// if no collision the first time simply insert
		int h1 = hash1(key);
		if(this.hashTable[h1] == -1) {
			this.hashTable[h1] = key;
			return;
		}
	
		
		// If a collision is encountered
		int h2 = hash2(key);
		int i = 1;
		
		int hashIndex = (h1 + (i*h2)) % this.size;
		
		while(true) {
			if(this.hashTable[hashIndex] == -1){
				this.hashTable[hashIndex] = key;
				break;
			}
			i += 1;
			hashIndex = (h1 + (i*h2)) % this.size;

		}
		this.numOfKeys += 1;
	}	
	
	public boolean search(int key) {
		
		// check if key is in its original index
		int h1 = hash1(key);
		if(this.hashTable[h1] == key) {
			return true;
		}
		
		
		//check if key is in any double hashed index
		int h2 = hash2(key);
		int i = 1;
		
		int hashIndex = (h1 + (i*h2)) % this.size;
		
		while(this.hashTable[hashIndex] != key) {
			if(this.hashTable[hashIndex] == -1) {
				return false;
			}
			i += 1;
			hashIndex = (h1 + (i*h2)) % this.size;
			
		}
		return true;
	}
	
	
	public static void main(String[] Args) {
		DoubleHash ht = new DoubleHash();
		
		
		//try a few insertions
		ht.insert(16);
		ht.insert(5);
		ht.insert(27);
		ht.insert(3);
		ht.insert(14);
		
		
		//try some searches
		System.out.println(ht.search(10));
		System.out.println(ht.search(20));
		
		System.out.println(ht.search(14));
		System.out.println(ht.search(16));
		System.out.println(ht.search(27));
		System.out.println(ht.search(27));
	}

}
