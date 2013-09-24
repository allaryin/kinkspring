package com.allaryin.kinkspring.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.allaryin.kinkspring.Kinkspring;
import com.allaryin.kinkspring.item.Items;

public enum Recipes {
	// TODO: redstone becomes an iron gear once BC4 is added
	spring(new ItemStack(Items.spring.item),
			new Object[] { "---", "O*O", "---", '-', Item.ingotIron, 'O',
					Item.slimeBall, '*', Item.redstone });


	private ItemStack	_stack;
	private Object[]	_recipe;

	private Recipes(ItemStack stack, Object[] recipe) {
		_stack = stack;
		_recipe = recipe;
	}

	public ItemStack getItemStack() {
		return _stack;
	}

	public Object[] getRecipe() {
		return _recipe;
	}

	public void updateRecipe(Object[] recipe) {
		Kinkspring.log.fine("Updating recipe for " + this.name() + "...");
		_recipe = recipe;
	}
}
