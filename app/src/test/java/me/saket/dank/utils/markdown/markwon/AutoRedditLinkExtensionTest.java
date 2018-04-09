package me.saket.dank.utils.markdown.markwon;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

import me.saket.dank.urlparser.UrlParserConfig;
import me.saket.dank.utils.markdown.markwon.AutoRedditLinkExtension.AutoRedditLinkPostProcessor;
import me.saket.dank.utils.markdown.markwon.AutoRedditLinkExtension.RedditLinkSpan;

public class AutoRedditLinkExtensionTest {

  @Test
  public void test() {
    UrlParserConfig urlParserConfig = new UrlParserConfig();
    AutoRedditLinkPostProcessor processor = new AutoRedditLinkPostProcessor(urlParserConfig);

    String markdown = "\\nSub: /r/pics r/pics\\n\\nUser: /u/Saketme/ u/saketme/\\n\\n---\\n\\n";
    List<RedditLinkSpan> links = processor.extractLinks(markdown);

    assertEquals(links.size(), 4);
    assertEquals(true, links.contains(RedditLinkSpan.create("https://reddit.com/r/pics", 7, 14)));
    assertEquals(true, links.contains(RedditLinkSpan.create("https://reddit.com/r/pics", 15, 21)));
    assertEquals(true, links.contains(RedditLinkSpan.create("https://reddit.com/u/Saketme", 31, 42)));
    assertEquals(true, links.contains(RedditLinkSpan.create("https://reddit.com/u/saketme", 43, 53)));
  }
}
