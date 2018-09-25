package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sss {

	/**
	 * 从 有值的 list 里取交集
	 * 
	 * @param lists
	 * @return
	 */
	public List<Object> intersection(List<List<Object>> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		ArrayList<List<Object>> arrayList = new ArrayList<>(lists);
		for (int i = 0; i < arrayList.size(); i++) {
			List<Object> list = arrayList.get(i);
			// 去除空集合
			if (list == null || list.size() == 0) {
				arrayList.remove(list);
				i--;
			}
		}
		if (arrayList.size() == 0) {
			return null;
		}
		List<Object> intersection = arrayList.get(0);
		// 就只有一个非空集合，结果就是他咯
		if (arrayList.size() == 1) {
			return intersection;
		}
		// 有多个非空集合，直接挨个交集
		for (int i = 1; i < arrayList.size() - 1; i++) {
			intersection.retainAll(arrayList.get(i));
		}
		return intersection;
	}

	public void test() {
		List<Object> list1 = new ArrayList<Object>();
		List<Object> list2 = new ArrayList<Object>();
		List<Object> list3 = new ArrayList<Object>();
		List<Object> list4 = new ArrayList<Object>();
		List<Object> list5 = new ArrayList<Object>();

		initList(list1);
		initList(list2);
		initList(list3, "3", "4", "5");
		initList(list4, "3", "4");
		initList(list5, "8", "3", "3", "4");

		System.out.println(intersection(Arrays.asList(list1, list2, list3,
				list4, list5)));

	}

	/**
	 * 给 List 添加元素
	 * 
	 * @param list
	 * @param item
	 */
	public void initList(List<Object> list, Object... item) {
		if (list == null) {
			throw new IllegalArgumentException("list can't be empty!");
		}
		if (item == null || item.length == 0) {
			return;
		}
		list.addAll(Arrays.asList(item));
	}

	public static void main(String[] args) {
		//new sss().test();
		List<String> a = new ArrayList<>();
		a.add("q");
		a.add("w");
		a.add("e");
		a.add("r");
		List<String> aq = new ArrayList<>();
		aq.add("r");
		aq.add("t");
		aq.add("t");
		aq.add("t");
		a.retainAll(aq);
		System.out.println(a.size());
		
		
	}

}
