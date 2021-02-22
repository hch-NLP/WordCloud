package nudt.com.wordcloud;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.image.AngleGenerator;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

/**
 * @author Chunhui He
 * @version V 1.0
 */
public class Test {
	public static void main(String[] args) throws Exception {
		Test App = new Test();
		String[] booklist = { "Spring实战", "Spring源码深度 解析", "SpringBoot实战", "SpringBoot2 精髓", "一步一步学SpringBoot2",
				"Spring微服务 实战", "Head First Java", "Java并发编程实战", "深入理解Java 虚拟机", "Head First Design", "effective java",
				"J2EE development without EJB", "TCP/IP卷一", " 计算机网络：自顶向下", "图解HTTP和图解TCP/IP", "计算机网络", "深入理解计算机系统",
				"现代操作系统", "Linux内核设计与实现", "Unix网络编程", "数据结构与算法", "算法导论", "数据结构与算法（Java版）", "算法图解，啊哈算法", "剑指offer",
				"LeetCode", " Java编程思想", "Java核心技术卷一", "深入理解JVM虚拟机", "Java并发编程实战", " Java并发编程艺术", "Java性能调优指南",
				"Netty权威指南", "深入JavaWeb技术内幕", "How Tomcat Works", "Tomcat架构解析", "Spring实战", "Spring源码深度解析",
				"Spring MVC学习指南", "Maven实战", "sql必知必会", "深入浅出MySQL", "Spring cloud微服务实战", "SpringBoot与Docker微服务实战",
				"深入理解SpringBoot与微服务架构" };
		App.generation_word_cloud(booklist);
	}

	private void generation_word_cloud(String[] wordlist) {
		List<WordFrequency> wordFrequencies = new ArrayList<>();
		Map<String, Integer> wf_tmp = new LinkedHashMap<String, Integer>();
		for (String keyword : wordlist) {
			if (wf_tmp.containsKey(keyword.replaceAll(" ", "").replaceAll("	", ""))) {
				wf_tmp.put(keyword.replaceAll(" ", "").replaceAll("	", ""), wf_tmp.get(keyword) + 100);
			} else {
				wf_tmp.put(keyword.replaceAll(" ", "").replaceAll("	", ""), 100);
			}
		}
		for (String word : wf_tmp.keySet()) {
			wordFrequencies.add(new WordFrequency(word, wf_tmp.get(word))); 
		}
		// 此处不设置会出现中文乱码
		// 三个参数分别指字体名称 风格 大小  STSong-Light
		java.awt.Font font = new java.awt.Font("新宋体", 3, 20);// 英文文本--> "Times New Roman",java.awt.Font.ITALIC,20
		//创建画布，并指定尺寸
		Dimension dimension = new Dimension(800, 800);
		//创建词云对象
		WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		//设置画布的边框
		wordCloud.setPadding(1);
		//设置圆形词云展示区域的半径
		wordCloud.setBackground(new CircleBackground(405));
		//wordCloud.setBackground(new RectangleBackground(dimension));
		//设置词云展示区域的字体角度  指定 0度-30度，步数
		wordCloud.setAngleGenerator(new AngleGenerator(-10,10,3));
		//设置词云中字体的最小尺寸和和最大尺寸
		wordCloud.setFontScalar(new SqrtFontScalar(14, 34));
		// 设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
		wordCloud.setColorPalette(new LinearGradientColorPalette(Color.BLUE, Color.MAGENTA, Color.RED, 30, 100));
		//把字体参数传给词云对象
		wordCloud.setKumoFont(new KumoFont(font));
		//设置词云展示区域的背景颜色
		wordCloud.setBackgroundColor(new Color(200, 255, 255));

		System.out.println("正在生成词云...");
		//根据词云对象的设置和关键词列表开始创建词云
		wordCloud.build(wordFrequencies);
		//为生成的词云指定本地保存路径
		wordCloud.writeToFile("C:/Users/lenovo/Desktop/test.png");
		System.out.println("词云生成完毕！");
	}
}
