package designpattern.builder;

public class Client {

	public static void main(String[] args) {
		CarDirectory lowDirectory = new LowCarDirectory(new LowCarBuilder());
		Car lowQualityCar = lowDirectory.directory();
		lowQualityCar.start();
		lowQualityCar.comfort();

		CarDirectory highDirectory = new HighCarDirectory(new LowCarBuilder());
		Car highQualityCar = highDirectory.directory();
		highQualityCar.start();
		highQualityCar.comfort();
		
	}

}
