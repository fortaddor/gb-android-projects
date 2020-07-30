package lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *Simple phonebook implementation.
 *
 * <author>Valerij Krauter</author>
 * <date>30.07.2020</date>
 */
public class Phonebook extends HashMap<String, List<String>>
{
    public void add(String name, String phonenumber)
    {
        if (!this.containsKey(name))
        {
            this.put(name, new ArrayList<>());
        }

        super.get(name).add(phonenumber);
    }

    public String get(String name)
    {
        return super.get(name).toString();
    }
}
