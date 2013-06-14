/**
 * 此为开源项目JakeWharton-Android-ViewPagerIndicator中的代码
 */
package com.example.android.pageindicator;

public interface IconPagerAdapter {
	
	// From PagerAdapter
    int getCount();
    
    CharSequence getPageTitle(int position);
	
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);
   
}