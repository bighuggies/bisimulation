package se705.bisimulation.util;

import java.util.Iterator;

public class StringUtil {
	public static String join(final Iterable<? extends Object> elements, final CharSequence separator)
	{
		StringBuilder builder = new StringBuilder();

		if (elements != null)
		{
			Iterator<? extends Object> iter = elements.iterator();
			if(iter.hasNext())
			{
				builder.append( String.valueOf( iter.next() ) );
				while(iter.hasNext())
				{
					builder
					.append( separator )
					.append( String.valueOf( iter.next() ) );
				}
			}
		}

		return builder.toString();
	}
}
