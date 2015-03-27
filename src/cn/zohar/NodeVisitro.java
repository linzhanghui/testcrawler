package cn.zohar;

import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.visitors.NodeVisitor;

public class NodeVisitro {
	public static void main(String[] args) {
		try{
		Parser parser = new Parser((HttpURLConnection)(new URL("http://www.163trade.com")).openConnection());
		NodeVisitor visitor = new NodeVisitor(false,false) {
			public void visitTag(Tag tag) {
				System.out.println("This is Tag:"+tag.getText());
			}
			public void visitStringNode(Text string) {
				System.out.println("This is Text:"+string);
			}
			public void visitRemarkNode(Remark remark) {
				System.out.println("This is Remark:"+remark.getText());
			}
			public void beginParsing() {
				System.out.println("beginParsing");
			}
			public void visitEndTag(Tag tag) {
				System.out.println("visitEndTag:"+tag.getText());
			}
			public void finishedParsing() {
				System.out.println("finishedParsing");
			}
		};
		parser.visitAllNodesWith(visitor);
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	}
}
