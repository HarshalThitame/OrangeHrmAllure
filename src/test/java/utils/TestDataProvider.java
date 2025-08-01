package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider
    public Object[][] getLoginData() {
        String path = "src/test/resources/testData/OrangeHRMLoginData.xlsx";
        return ExcelUtils.getTestData(path, "LoginData");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"", "", "Required", "Required"}, // both empty
                {"Admin", "", "", "Required"}, // password empty
                {"", "admin123", "Required", ""}, // username empty
                {"wronguser", "wrongpass", "", ""}, // invalid credentials (no "Required" if filled)
        };
    }

    @DataProvider(name = "amountBVAData")
    public Object[][] amountTestData() {
        return new Object[][]{
                {"39999", true, "Should be within Min/Max values"},
                {"40000", false, ""},
                {"40001", false, ""},
                {"49999", false, ""},
                {"50000", false, ""},
                {"50001", true, "Should be within Min/Max values"}
        };
    }
}
