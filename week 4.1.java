class Solution {
    public boolean halvesAreAlike(String s) {
        int mid = s.length() / 2;
        String left = s.substring(0,mid);
        String right = s.substring(mid);
        int count = 0;
        int sum = 0;
        for (int i = 0; i < left.length(); i++){
            char k = left.charAt(i);
            char ch = Character.toLowerCase(k); 
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                count++;
            }
        }
        for (int i = 0; i < right.length(); i++){
            char k = right.charAt(i);
            char ch = Character.toLowerCase(k); 
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                sum++;
        }
      }

         return count == sum;

     
    }
}