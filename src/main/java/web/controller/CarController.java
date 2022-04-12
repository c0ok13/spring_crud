package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

	@GetMapping(value = "/cars")
	public String printWelcome(@RequestParam(value = "count", required = false) String count, ModelMap model) {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("aaa", 1111));
		cars.add(new Car("bbb", 22222));
		cars.add(new Car("ccc", 3333));
		cars.add(new Car("ddd", 4444));
		cars.add(new Car("fff", 5555));

		try {
			if (count != null && Integer.parseInt(count) < 5  && Integer.parseInt(count) > 0 ) {
				cars = cars.subList(0, Integer.parseInt(count));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		model.addAttribute("messages", cars);
		System.out.println(cars.toString());
		return "cars";
	}
	
}