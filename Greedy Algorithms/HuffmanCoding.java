
import java.util.PriorityQueue;
import java.util.HashMap;

public class HuffmanCoding {

    static class Node implements Comparable<Node> {
        char character;
        int frequency;
        Node left, right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            left = null;
            right = null;
        }

        // Used for priority queue ordering
        public int compareTo(Node node) {
            return this.frequency - node.frequency;
        }
    }

    // Function to construct the Huffman Tree
    static Node buildHuffmanTree(HashMap<Character, Integer> freqMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        
        for (char c : freqMap.keySet()) {
            priorityQueue.offer(new Node(c, freqMap.get(c)));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            priorityQueue.offer(parent);
        }
        return priorityQueue.poll();
    }
    static void generateHuffmanCodes(Node root, String code, HashMap<Character, String> huffmanCodes) {
        if (root == null) return;

        if (root.character != '\0') {
            huffmanCodes.put(root.character, code);
        }

        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }
    static String encode(String str, HashMap<Character, String> huffmanCodes) {
        StringBuilder encodedString = new StringBuilder();
        for (char c : str.toCharArray()) {
            encodedString.append(huffmanCodes.get(c));
        }
        return encodedString.toString();
    }
    static String decode(Node root, String encodedString) {
        StringBuilder decodedString = new StringBuilder();
        Node current = root;
        for (int i = 0; i < encodedString.length(); i++) {
            char bit = encodedString.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedString.append(current.character);
                current = root;
            }
        }

        return decodedString.toString();
    }

    public static void main(String[] args) {
        String str = "ASSIGNMENT NUMBER 5 - GREEDY ALGORITHMS - HUFFMAN CODING";
        
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        Node root = buildHuffmanTree(freqMap);

        
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        
        String encodedString = encode(str, huffmanCodes);
        System.out.println("Encoded String: " + encodedString);

        
        String decodedString = decode(root, encodedString);
        System.out.println("Decoded String: " + decodedString);
    }
}
