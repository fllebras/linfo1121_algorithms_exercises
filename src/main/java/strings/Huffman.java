package strings;


import java.util.PriorityQueue;

/**
 * This class is used to construct a Huffman trie from frequencies of letters (in unicode or ASCII).
 * As a reminder, in a Huffman trie nodes are weighted (see the `HuffmanNode` class) by
 * the frequencies of the character (if lead node) or the sum of the frequencies of its children
 * (if internal node).
 * For example, let us assume that we have the following letters with their associated frequencies:
 *  (t, 1), (m, 2), (z, 3), (a, 4), (g, 5)
 *
 *  The the following Huffman trie can be constructed
 *
 *
 *                      (_, 15)
 *                         |
 *         (_, 9) -------------------- (_, 6)
 *           |                           |
 *  (a, 4)------(g, 5)        (z, 3)----------(_, 3)
 *                                              |
 *                                     (t, 1)------(m, 2)
 *
 * In practice you are given an array of frequencies for each of the 256 ASCII code or 65536 unicode characters.
 * The goal is to construct the Huffman trie from this array of frequencies.
 */
public class Huffman {

    /**
     * Constructs a Huffman trie for the frequencies of the characters given in arguments.
     * The characters are implicitely defined by the `freq` array (ranging from 0 to freq.length -1)
     *
     * @param freq the frequencies of the characters, freq[i] = frequency of character i
     */
    public static HuffmanNode buildTrie(int [] freq) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for(int i = 0; i<freq.length; i++){
            pq.add(new HuffmanNode(i,freq[i], null, null));
        }
        while(pq.size()>1){
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();
            HuffmanNode parent = new HuffmanNode(-1,x.getFrequency()+y.getFrequency(),x,y);
            pq.add(parent);
        }
        return pq.poll();
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {

    private final int ch;
    private final int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode getLeft() {
        return this.left;
    }

    @SuppressWarnings("unchecked")
    public void setLeft(HuffmanNode node) {
        this.left = node;
    }

    public HuffmanNode getRight() {
        return this.right;
    }

    @SuppressWarnings("unchecked")
    public void setRight(HuffmanNode node) {
        this.right = node;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.freq - node.freq;
    }

    public int getFrequency() {
        return this.freq;
    }

    public int getChar() {
        return this.ch;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
