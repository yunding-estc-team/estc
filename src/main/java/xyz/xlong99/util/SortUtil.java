package xyz.xlong99.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xlong
 * @date 2019-08-10 08:55
 */
public class SortUtil {
    /** 驼峰转下划线,效率比上面高 */
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String humpToLine2(String str) {

        Matcher matcher = humpPattern.matcher(str);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {

            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());

        }

        matcher.appendTail(sb);

        return sb.toString();

    }
    public static String toSortSql(String sort,String order){
        String sortSql = "ORDER BY ";
        if(sort.equals("") ){
            return "";
        }else{
        String[] sortArray = sort.split(",");
        String[] orderArray = order.split(",");
        for (int i = 1 ; i <= sortArray.length; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(humpToLine2(sortArray[i-1])).append(" ").append(orderArray[i-1]);
            if( i < sortArray.length){
                sb.append(",");
            }
            sortSql = sortSql + sb;
        }
        return sortSql;
        }
    }

}
