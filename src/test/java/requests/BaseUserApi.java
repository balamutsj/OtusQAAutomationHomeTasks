package requests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import data.EndPoints;

import java.util.Locale;

public class BaseUserApi {

    protected static String baseUrl = EndPoints.basePath;
    protected static Faker faker = new Faker(new Locale("en-GB"));
    protected static FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());//jenkins integration commit
}
