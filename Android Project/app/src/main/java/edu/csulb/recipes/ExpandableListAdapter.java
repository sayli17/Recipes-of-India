package edu.csulb.recipes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.*;
import java.util.HashMap;

@SuppressLint("UseSparseArrays")
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    // Define activity context
    private Context mContext;

    /*
     * Here we have a Hashmap containing a String key
     * (can be Integer or other type but I was testing
     * with contacts so I used contact name as the key)
    */
    private HashMap<String, List<String>> mListDataChild;


    // ArrayList that is what each key in the above
    // hashmap points to
    private ArrayList<String> mListDataGroup;

    // Hashmap for keeping track of our checkbox check states
    private HashMap<Integer, boolean[]> mChildCheckStates;

    // Our getChildView & getGroupView use the viewholder patter
    // Here are the viewholders defined, the inner classes are
    // at the bottom
    private ChildViewHolder childViewHolder;
    private GroupViewHolder groupViewHolder;

    /*
          *  For the purpose of this document, I'm only using a single
     *	textview in the group (parent) and child, but you're limited only
     *	by your XML view for each group item :)
    */
    private String groupText;
    private String childText;
    ArrayList<String> selectedItems = new ArrayList<String>();



    /*  Here's the constructor we'll use to pass in our calling
                 *  activity's context, group items, and child items
                */
    public ExpandableListAdapter(Context context, ArrayList<String> listDataGroup,HashMap<String, List<String>> listDataChild){

        mContext = context;
        mListDataGroup = listDataGroup;
        mListDataChild = listDataChild;

        // Initialize our hashmap containing our check states here
        mChildCheckStates = new HashMap<Integer, boolean[]>();


    }

    @Override
    public int getGroupCount() {
        return mListDataGroup.size();
    }

    /*
     * This defaults to "public object getGroup" if you auto import the methods
     * I've always make a point to change it from "object" to whatever item
     * I passed through the constructor
    */
    @Override
    public String getGroup(int groupPosition) {
        return mListDataGroup.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        //  I passed a text string into an activity holding a getter/setter
        //  which I passed in through "ExpListGroupItems".
        //  Here is where I call the getter to get that text
        groupText = getGroup(groupPosition);

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);

            // Initialize the GroupViewHolder defined at the bottom of this document
            groupViewHolder = new GroupViewHolder();

            groupViewHolder.mGroupText = (TextView) convertView.findViewById(R.id.lblListHeader);

            convertView.setTag(groupViewHolder);
        } else {

            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.mGroupText.setText(groupText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListDataChild.get(mListDataGroup.get(groupPosition)).size();
    }

    /*
     * This defaults to "public object getChild" if you auto import the methods
     * I've always make a point to change it from "object" to whatever item
     * I passed through the constructor
    */
    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mListDataChild.get(mListDataGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final int mGroupPosition = groupPosition;
        final int mChildPosition = childPosition;

        //  I passed a text string into an activity holding a getter/setter
        //  which I passed in through "ExpListChildItems".
        //  Here is where I call the getter to get that text
        childText = getChild(mGroupPosition, mChildPosition);

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            childViewHolder = new ChildViewHolder();

            childViewHolder.mChildText = (TextView) convertView
                    .findViewById(R.id.lblListItem);

            childViewHolder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.lstcheckBox);

            convertView.setTag(R.layout.list_item, childViewHolder);

        } else {

            childViewHolder = (ChildViewHolder) convertView
                    .getTag(R.layout.list_item);
        }

        childViewHolder.mChildText.setText(childText);

		/*
		 * You have to set the onCheckChangedListener to null
		 * before restoring check states because each call to
		 * "setChecked" is accompanied by a call to the
		 * onCheckChangedListener
		*/
        childViewHolder.mCheckBox.setOnCheckedChangeListener(null);

        if (mChildCheckStates.containsKey(mGroupPosition)) {
			/*
			 * if the hashmap mChildCheckStates<Integer, Boolean[]> contains
			 * the value of the parent view (group) of this child (aka, the key),
			 * then retrive the boolean array getChecked[]
			*/
            boolean getChecked[] = mChildCheckStates.get(mGroupPosition);

            // set the check state of this position's checkbox based on the
            // boolean value of getChecked[position]
            childViewHolder.mCheckBox.setChecked(getChecked[mChildPosition]);

        } else {

			/*
			 * if the hashmap mChildCheckStates<Integer, Boolean[]> does not
			 * contain the value of the parent view (group) of this child (aka, the key),
			 * (aka, the key), then initialize getChecked[] as a new boolean array
			 *  and set it's size to the total number of children associated with
			 *  the parent group
			*/
            boolean getChecked[] = new boolean[getChildrenCount(mGroupPosition)];

            // add getChecked[] to the mChildCheckStates hashmap using mGroupPosition as the key
            mChildCheckStates.put(mGroupPosition, getChecked);

            // set the check state of this position's checkbox based on the
            // boolean value of getChecked[position]
            childViewHolder.mCheckBox.setChecked(false);
        }

        childViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    boolean getChecked[] = mChildCheckStates.get(mGroupPosition);
                    getChecked[mChildPosition] = isChecked;
                    mChildCheckStates.put(mGroupPosition, getChecked);

                    String item = getSelectedItem(mGroupPosition, mChildPosition);
                    selectedItems.add(item);
                } else {

                    boolean getChecked[] = mChildCheckStates.get(mGroupPosition);
                    getChecked[mChildPosition] = isChecked;
                    mChildCheckStates.put(mGroupPosition, getChecked);
                }
                //System.out.print(selectedItems);
            }
        });

        return convertView;
    }

    public String getSelectedItem(int groupid, int childid){
        HashMap<Integer, String> veggies = new HashMap<Integer, String>();
        veggies.put(0, "onion");
        veggies.put(1, "tomato");
        veggies.put(2, "potato");
        veggies.put(3, "carrot");
        veggies.put(4, "capsicum");
        veggies.put(5, "cabbage");
        veggies.put(6, "cauliflower");
        veggies.put(7, "green peas");
        veggies.put(8, "french beans");

        HashMap<Integer, String> meat = new HashMap<Integer, String>();
        meat.put(0, "onion");
        meat.put(1, "tomato");
        meat.put(2, "potato");
        meat.put(3, "carrot");
        meat.put(4, "capsicum");

        HashMap<Integer, String> milk_products = new HashMap<Integer, String>();
        milk_products.put(0, "cottage cheese");
        milk_products.put(1, "milk");
        milk_products.put(2, "yogurt");
        milk_products.put(3, "mozzarella");
        milk_products.put(4, "butter");

        HashMap<Integer, String> fruits = new HashMap<Integer, String>();
        fruits.put(0, "apple");
        fruits.put(1, "mango");
        fruits.put(2, "banana");
        fruits.put(3, "grapes");
        fruits.put(4, "strawberry");
        fruits.put(5, "blueberry");
        fruits.put(6, "watermelon");
        fruits.put(7, "pineapple");

        HashMap<Integer, String> spices = new HashMap<Integer, String>();
        spices.put(0, "chillies");
        spices.put(1, "cilantro");
        spices.put(2, "mint");
        spices.put(3, "ginger");
        spices.put(4, "garlic");
        fruits.put(5, "pepper");
        fruits.put(6, "cumin");

        HashMap<Integer, String> pantry = new HashMap<Integer, String>();
        pantry.put(0, "salt");
        pantry.put(1, "sugar");
        pantry.put(2, "eggs");
        pantry.put(3, "rice");
        pantry.put(4, "oil");
        pantry.put(5, "chickpeas flour");

        String selected_item = null;

        switch (groupid){
            case 0:
                selected_item = veggies.get(childid);
                break;
            case 1:
                selected_item = meat.get(childid);
                break;
            case 2:
                selected_item = milk_products.get(childid);
                break;
            case 3:
                selected_item = fruits.get(childid);
                break;
            case 4:
                selected_item = spices.get(childid);
                break;
            case 5:
                selected_item = pantry.get(childid);
                break;
            default:
                break;
        }

        return selected_item;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public final class GroupViewHolder {

        TextView mGroupText;
    }

    public final class ChildViewHolder {

        TextView mChildText;
        CheckBox mCheckBox;
    }


}
