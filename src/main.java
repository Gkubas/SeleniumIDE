import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {

	public static void main(String[] args) throws InterruptedException {
		String wartoscitestow = "";
		wartoscitestow+=("1: " + testy("Jan", "Kowalski", "01-01-2008", true, true, "Junior"))+"\n";
		wartoscitestow+=("2: " + testy("Jan", "Kowalski", "01-01-2008", false, false, "Blad Danych"))+"\n";
		wartoscitestow+=("3: " + testy("Jan", "Kowalski", "12-05-2008", true, true, "Młodzik"))+"\n";
		wartoscitestow+=("4: " + testy("Jan", "Kowalski", "12-05-1950", false, true, "Senior"))+"\n";
		wartoscitestow+=("5: " + testy("Jan", "Kowalski", "12-05-1950", false, false, "Brak kwalifikacji"))+"\n";
		wartoscitestow+=("6: " + testy("Jan", "Kowalski", "12-05-2013", true, true, "Brak kwalifikacji"))+"\n";
		wartoscitestow+=("7: " + testy("Jan", "Kowalski", "12-05-2008", true, false, "Blad danych"))+"\n";
		wartoscitestow+=("8: " + testy("Jan", "Kowalski", "12-05-2008", false, true, "Blad danych"))+"\n";
		wartoscitestow+=("9: " + testy("Jan", "Kowalski", "12-05-1954", false, false, "Dorosly"))+"\n";
		wartoscitestow+=("10: " + testy("Jan", "Kowalski", "12-05-1996", true, false, "Dorosly"))+"\n";
		wartoscitestow+=("11: " + testy("Jan", "Kowalski", "12-05-1948", true, false, "Blad danych"))+"\n";
		wartoscitestow+=("12: " + testy("Jan", "Kowalski", "12-05-1815", false, false, "Blad danych"))+"\n";
	}
	public static String testy(String imieW, String nazwiskoW, String wiek, boolean rodzicZgoda, boolean lekarzZgoda,
			String odpStrony) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver74.exe");
		WebDriver wd = new ChromeDriver();
		wd.get("https://lamp.ii.us.edu.pl/~mtdyd/zawody/");
		Thread.sleep(600);
		WebElement imie = wd.findElement(By.id("inputEmail3"));
		WebElement nazwisko = wd.findElement(By.id("inputPassword3"));
		WebElement data = wd.findElement(By.id("dataU"));
		WebElement rg = wd.findElement(By.id("rodzice"));
		WebElement lz = wd.findElement(By.id("lekarz"));
		WebElement btn = wd.findElement(By.className("btn-default"));
		imie.sendKeys(imieW);
		nazwisko.sendKeys(nazwiskoW);
		data.sendKeys(wiek);
		if (rodzicZgoda) {
			rg.click();
		}
		if (lekarzZgoda) {
			lz.click();
		}
		btn.click();
		Thread.sleep(600);
		wd.switchTo().alert().accept();
		Thread.sleep(100);
		wd.switchTo().alert().accept();
		WebElement odpowiedz = wd.findElement(By.id("returnSt"));
		if (odpowiedz.getText().contains(odpStrony)) {
			wd.close();
			return "Test wykonany poprawnie";
		}
		wd.close();
		return "Test zakończony niepowodzeniem";
	}
}