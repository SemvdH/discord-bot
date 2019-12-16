import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearch {

	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

	public static String search(String tag) throws IOException {
		// can only grab first 100 results
		tag = convertToUrl(tag);
		String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
		String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=" + tag + "&gws_rd=cr";

		List<String> resultUrls = new ArrayList<String>();
		String resUrl = "";
		try {
			Document doc = Jsoup.connect(url).userAgent(userAgent).referrer("https://www.google.com/").get();

			Elements elements = doc.select("div.rg_meta");

			JSONObject jsonObject;
			for (Element element : elements) {
				if (element.childNodeSize() > 0) {
					jsonObject = (JSONObject) new JSONParser().parse(element.childNode(0).toString());
					resultUrls.add((String) jsonObject.get("ou"));
				}
			}

			System.out.println("number of results: " + resultUrls.size());

			for (String imageUrl : resultUrls) {
				System.out.println(imageUrl);
			}
			if (resultUrls.isEmpty() || resultUrls.size() == 0) {
				resUrl = "No results found";
			} else {
				Random random = new Random();
				int choice = random.nextInt(resultUrls.size() + 1);
				resUrl = resultUrls.get(choice);
			}

		} catch (ParseException e) {
			resUrl = "There was an error!";
			e.printStackTrace();
		}
		return resUrl;
	}

	public static String convertToUrl(String text) {
		String[] res = text.split(" ");
		String result = "";
		for (int i = 0; i < res.length; i++) {
			result += res[i];
			if (i != res.length - 1) {
				result += "+";
			}
		}
		System.out.println("converted [" + text + "] to [" + result + "]");
		return result;
	}

}