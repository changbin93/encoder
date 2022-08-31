public class Encoder {

    char[] table = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
                    'Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5',
                    '6','7','8','9','(',')','*','+',',','-','.','/'};

    /**
     * encode the plainText
     * @param plainText
     * @return encoded text
     */
    public String encode(String plainText) {
        if (plainText == null || plainText.equals("")) return "";
        StringBuilder sb = new StringBuilder();
        int offset = (int) (Math.random() * table.length);
        sb.append(table[offset]);
        for(char c:plainText.toCharArray()) {
            int index = getIndex(c);
            if (index != -1){
                char offset_char = table[(index - offset + table.length) % table.length];
                sb.append(offset_char);
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * decode the encodedText to original text
     * @param encodedText
     * @return original text
     */
    public String decode(String encodedText) {
        if (encodedText == null || encodedText.equals("")) return "";
        StringBuilder sb = new StringBuilder();
        char offsetChar = encodedText.charAt(0);
        encodedText = encodedText.substring(1);
        int offset = getIndex(offsetChar);
        for(char c:encodedText.toCharArray()) {
            int index = getIndex(c);
            if(index != -1) {
                char original_char = table[(index + offset) % table.length];
                sb.append(original_char);
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * get the index of the character in the table
     * @param c
     * @return index
     */
    private int getIndex(char c) {
        for(int i = 0;i < table.length;i++) {
            if(table[i] == c) {
                return i;
            }
        }
        return -1;
    }

    //test
    public static void main(String[] args) {
        Encoder e = new Encoder();
        String encoded = e.encode("");
        System.out.println(encoded);
        String original = e.decode(encoded);
        System.out.println(original);
    }
}
