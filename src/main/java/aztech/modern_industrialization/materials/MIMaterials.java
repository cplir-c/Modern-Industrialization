/*
 * MIT License
 *
 * Copyright (c) 2020 Azercoco & Technici4n
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package aztech.modern_industrialization.materials;

import static aztech.modern_industrialization.materials.part.MIParts.*;
import static aztech.modern_industrialization.materials.property.MaterialHardness.*;
import static aztech.modern_industrialization.materials.set.MaterialSet.*;

import aztech.modern_industrialization.MIItem;
import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.compat.kubejs.KubeJSFacade;
import aztech.modern_industrialization.machines.init.MIMachineRecipeTypes;
import aztech.modern_industrialization.materials.part.ExternalPart;
import aztech.modern_industrialization.materials.part.MIItemPart;
import aztech.modern_industrialization.materials.part.Part;
import aztech.modern_industrialization.materials.part.RegularPart;
import aztech.modern_industrialization.materials.property.ColorampParameters;
import aztech.modern_industrialization.materials.property.MaterialProperty;
import aztech.modern_industrialization.materials.recipe.ForgeHammerRecipes;
import aztech.modern_industrialization.materials.recipe.SmeltingRecipes;
import aztech.modern_industrialization.materials.recipe.StandardRecipes;
import aztech.modern_industrialization.materials.recipe.builder.MIRecipeBuilder;
import aztech.modern_industrialization.materials.set.MaterialBlockSet;
import aztech.modern_industrialization.materials.set.MaterialOreSet;
import aztech.modern_industrialization.materials.set.MaterialRawSet;
import aztech.modern_industrialization.nuclear.INeutronBehaviour;
import aztech.modern_industrialization.nuclear.NuclearAbsorbable;
import aztech.modern_industrialization.nuclear.NuclearConstant;
import net.minecraft.util.valueproviders.UniformInt;

// @formatter:off
public class MIMaterials {
    public static final Material ALUMINUM;
    public static final Material ANNEALED_COPPER;
    public static final Material ANTIMONY;
    public static final Material BATTERY_ALLOY;
    public static final Material BAUXITE;
    public static final Material BERYLLIUM;
    public static final Material BLASTPROOF_ALLOY;
    public static final Material BRICK;
    public static final Material BRONZE;
    public static final Material CADMIUM;
    public static final Material CARBON;
    public static final Material CHROMIUM;
    public static final Material COAL;
    public static final Material COKE;
    public static final Material COPPER;
    public static final Material CUPRONICKEL;
    public static final Material DIAMOND;
    public static final Material ELECTRUM;
    public static final Material EMERALD;
    public static final Material FIRE_CLAY;
    public static final Material GOLD;
    public static final Material HE_MOX;
    public static final Material HE_URANIUM;
    public static final Material INVAR;
    public static final Material IRIDIUM;
    public static final Material IRON;
    public static final Material KANTHAL;
    public static final Material LAPIS;
    public static final Material LEAD;
    public static final Material LE_MOX;
    public static final Material LE_URANIUM;
    public static final Material LIGNITE_COAL;
    public static final Material MANGANESE;
    public static final Material MOZANITE;
    public static final Material NEODYMIUM;
    public static final Material NICKEL;
    public static final Material NUCLEAR_ALLOY;
    public static final Material PLATINUM;
    public static final Material PLUTONIUM;
    public static final Material QUARTZ;
    public static final Material REDSTONE;
    public static final Material RUBY;
    public static final Material SALT;
    public static final Material SILICON;
    public static final Material SILVER;
    public static final Material SODIUM;
    public static final Material SOLDERING_ALLOY;
    public static final Material STAINLESS_STEEL;
    public static final Material STEEL;
    public static final Material SULFUR;
    public static final Material SUPERCONDUCTOR;
    public static final Material TIN;
    public static final Material TITANIUM;
    public static final Material TUNGSTEN;
    public static final Material URANIUM;
    public static final Material URANIUM_235;
    public static final Material URANIUM_238;
    public static final Material YTTRIUM;

    public static void init() {
        // init static
    }

    public static final String commonPath = "modern_industrialization:textures/materialsets/common/";
    public static final String templatePath = "modern_industrialization:textures/template/";
    public static final String mcPath = "minecraft:textures/item/";
    public static final String miPath = "modern_industrialization:textures/item/";

    public static String common(String name) {
        return commonPath + name + ".png";
    }

    public static String common(Part part) {
        return common(part.key);
    }

    public static String mcitem(String name) {
        return mcPath + name + ".png";
    }

    public static String miitem(String name) {
        return miPath + name + ".png";
    }

    public static String template(String name) {
        return templatePath + name + ".png";
    }

    public static MaterialBuilder addVanillaMetal(boolean nugget, MaterialBuilder builder) {
        String n = builder.getMaterialName();
        MaterialBuilder res = builder.overridePart(ExternalPart.of(INGOT, "#c:" + n + "_ingots", "minecraft:" + n + "_ingot"))
                .addParts(ExternalPart.of(BLOCK, "#c:" + n + "_blocks", "minecraft:" + n + "_block"))
                .addParts(ExternalPart.of(ORE, "#c:" + n + "_ores", "minecraft:" + n + "_ore"))
                .addParts(ExternalPart.of(ORE_DEEPSLATE, "#c:" + n + "_ores", "minecraft:deepslate_" + n + "_ore"))
                .addParts(ExternalPart.of(RAW_METAL, "#c:raw_" + n + "_ores", "minecraft:raw_" + n))
                .addParts(ExternalPart.of(RAW_METAL_BLOCK, "#c:raw_" + n + "_blocks", "minecraft:raw_" + n + "_block"));

        if (nugget) {
            res.overridePart(ExternalPart.of(NUGGET, "#c:" + n + "_nuggets", "minecraft:" + n + "_nugget"));
        }
        res.addRecipes(ForgeHammerRecipes::apply, SmeltingRecipes::apply, StandardRecipes::apply)
                .cancelRecipes("craft/block_from_ingot", "craft/ingot_from_block")
                .cancelRecipes("craft/raw_metal_block_from_raw_metal", "craft/raw_metal_from_raw_metal_block")
                .cancelRecipes("smelting/ore_to_ingot_smelting", "smelting/ore_to_ingot_blasting")
                .cancelRecipes("smelting/ore_deepslate_to_ingot_smelting", "smelting/ore_deepslate_to_ingot_blasting")
                .cancelRecipes("smelting/raw_metal_to_ingot_smelting", "smelting/raw_metal_to_ingot_blasting");
        if (nugget) {
            res.cancelRecipes("craft/ingot_from_nugget", "craft/nugget_from_ingot");
        }

        return res;
    }

    public static MaterialBuilder addVanillaGem(boolean compressor, MaterialBuilder builder) {
        return addVanillaGem(compressor, builder.getMaterialName(), builder);
    }

    public static MaterialBuilder addVanillaGem(boolean compressor, String gemPath, MaterialBuilder builder) {
        String n = builder.getMaterialName();
        MaterialBuilder res = builder.addParts(ExternalPart.of(GEM, "minecraft:" + gemPath, "minecraft:" + gemPath))
                .addParts(ExternalPart.of(BLOCK, "#c:" + n + "_blocks", "minecraft:" + n + "_block"))
                .addParts(ExternalPart.of(ORE, "#c:" + n + "_ores", "minecraft:" + n + "_ore"))
                .addParts(ExternalPart.of(ORE_DEEPSLATE, "#c:" + n + "_ores", "minecraft:deepslate_" + n + "_ore"));

        res.addRecipes(SmeltingRecipes::apply, StandardRecipes::apply).cancelRecipes("craft/block_from_gem", "craft/gem_from_block")
                .cancelRecipes("smelting/ore_to_gem_smelting", "smelting/ore_to_gem_blasting")
                .cancelRecipes("smelting/ore_deepslate_to_gem_smelting", "smelting/ore_deepslate_to_gem_blasting");

        if (compressor) {
            res.addRecipes(context -> new MIRecipeBuilder(context, MIMachineRecipeTypes.COMPRESSOR, n).addTaggedPartInput(DUST, 1).addItemOutput("minecraft:" + gemPath, 1));
        }
        return res;
    }

    static {
        GOLD = MaterialRegistry.addMaterial(addVanillaMetal(true,
                new MaterialBuilder("Gold", "gold")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xFFE650, common(INGOT), mcitem("gold_ingot")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(BOLT, RING, GEAR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .addParts(DRILL_HEAD, DRILL))
                );

        IRON = MaterialRegistry.addMaterial(
                        addVanillaMetal(true,
                                new MaterialBuilder("Iron", "iron")
                        .set(MaterialProperty.SET, METALLIC)
                                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xC8C8C8, common(INGOT), mcitem("iron_ingot")))
                                        .addParts(BOLT, RING, GEAR, ROD, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                                        .addParts(ExternalPart.of(HAMMER, MIItem.IRON_HAMMER.getId().toString())))
                                );

        COPPER = MaterialRegistry.addMaterial(addVanillaMetal(false,
                new MaterialBuilder("Copper", "copper")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xe77c56, common(INGOT), mcitem("copper_ingot")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(BOLT, BLADE, RING, ROTOR, GEAR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .addParts(WIRE)
                        .addParts(FINE_WIRE)
                        .addParts(CABLE.of(CableTier.LV))
                        .addParts(DRILL_HEAD, DRILL))
                .cancelRecipes("macerator/ore_to_raw", "forge_hammer/ore_to_raw_metal",
                        "forge_hammer/ore_to_raw_metal_with_tool", "forge_hammer/ore_to_dust_with_tool"));

        COAL = MaterialRegistry.addMaterial(addVanillaGem(true,
                new MaterialBuilder("Coal", "coal")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x282828, common(PLATE), mcitem("coal")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(ITEM_PURE_NON_METAL))
                .addRecipes(ForgeHammerRecipes::apply)
                );

        DIAMOND = MaterialRegistry.addMaterial(addVanillaGem(false,
                new MaterialBuilder("Diamond", "diamond")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x48eeda, mcitem("diamond"), mcitem("diamond")))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                        .addParts(ITEM_PURE_NON_METAL)
                        .addParts(PLATE, LARGE_PLATE)
                        .addParts(ExternalPart.of(HAMMER, MIItem.DIAMOND_HAMMER.getId().toString())))
                );

        EMERALD = MaterialRegistry.addMaterial(addVanillaGem(false,
                new MaterialBuilder("Emerald", "emerald")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x3FF385, mcitem("emerald"), mcitem("emerald")))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                        .addParts(ITEM_PURE_NON_METAL).addParts(PLATE)));

        LAPIS = MaterialRegistry.addMaterial(addVanillaGem(true, "lapis_lazuli",
                new MaterialBuilder("Lapis Lazuli", "lapis")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x1A2D8D, common("dust"), mcitem("lapis_lazuli")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(ITEM_PURE_NON_METAL).addParts(PLATE)).cancelRecipes("macerator/ore_to_crushed"));

        REDSTONE = MaterialRegistry
                .addMaterial(new MaterialBuilder("Redstone", "redstone")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xd20000, common(DUST), mcitem("redstone")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(TINY_DUST, CRUSHED_DUST, BATTERY).addParts(ExternalPart.of(DUST, "minecraft:redstone", "minecraft:redstone"))
                        .addParts(ExternalPart.of(BLOCK, "#c:redstone_blocks", "minecraft:redstone_block"))
                        .addParts(ExternalPart.of(ORE, "#c:redstone_ores", "minecraft:redstone_ore"))
                        .addParts(ExternalPart.of(ORE_DEEPSLATE, "#c:redstone_ores", "minecraft:deepslate_redstone_ore"))
                        .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply).cancelRecipes("macerator/ore_to_crushed")
                        .cancelRecipes("craft/block_from_dust", "craft/dust_from_block"));

        QUARTZ = MaterialRegistry
                .addMaterial(new MaterialBuilder("Quartz", "quartz")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xf0ebe4, mcitem("quartz"), mcitem("quartz")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(CRUSHED_DUST, DUST, TINY_DUST).addParts(ORE.of(UniformInt.of(2, 5), MaterialOreSet.QUARTZ))
                        .addParts(ExternalPart.of(GEM, "minecraft:quartz", "minecraft:quartz")).addRecipes(StandardRecipes::apply)
                        .cancelRecipes("macerator/ore_to_crushed")
                                .addRecipes(context -> {
                                    new MIRecipeBuilder(context, MIMachineRecipeTypes.COMPRESSOR, "quartz")
                                            .addTaggedPartInput(DUST, 1).addItemOutput("minecraft:quartz", 1);
                                })
                        );

        BRICK = MaterialRegistry.addMaterial(new MaterialBuilder("Brick", "brick")
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xb75a36, common("ingot"), mcitem("brick")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                .addParts(DUST, TINY_DUST)
                .addParts(ExternalPart.of(INGOT, "minecraft:brick", "minecraft:brick"))
                .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        FIRE_CLAY = MaterialRegistry.addMaterial(
                new MaterialBuilder("Fire Clay", "fire_clay")
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xb75a36, common("ingot"), miitem("fire_clay_brick")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(DUST)
                        .addParts(MIItemPart.of(INGOT, "Fire Clay Brick", "fire_clay_brick"))
                        .addRecipes(SmeltingRecipes::apply));

        COKE = MaterialRegistry
                .addMaterial(new MaterialBuilder("Coke", "coke")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x6d6d57, common("dust"), miitem("coke")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(DUST)
                        .addParts(MIItemPart.of(GEM, "Coke", "coke"))
                        .addParts(BLOCK.of(MaterialBlockSet.COAL))
                        .addRecipes(context -> {
                            new MIRecipeBuilder(context, MIMachineRecipeTypes.COMPRESSOR, "dust").addTaggedPartInput(DUST, 1).addPartOutput(GEM, 1);
                        })
                        .addRecipes(StandardRecipes::apply));

        BRONZE = MaterialRegistry.addMaterial(
                new MaterialBuilder("Bronze", "bronze")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xffcc00, common("ingot"), template("bronze_ingot")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(BOLT, BLADE, RING, ROTOR, GEAR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .removeParts(CRUSHED_DUST).addParts(BLOCK.of(MaterialBlockSet.COPPER)).addParts(TANK.of(4)).addParts(DRILL_HEAD, DRILL)
                        .addParts(BARREL.of(32)).addParts(MACHINE_CASING, MACHINE_CASING_PIPE)
                        .addParts(MACHINE_CASING_SPECIAL.of("Bronze Plated Bricks", "bronze_plated_bricks"))
                        .addRecipes(ForgeHammerRecipes::apply, SmeltingRecipes::apply, StandardRecipes::apply));

        TIN = MaterialRegistry
                .addMaterial(new MaterialBuilder("Tin", "tin")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xc0bcd0, common("ingot"), template("tin_ingot")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(BOLT, BLADE, RING, ROTOR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .addParts(ORE.ofAll(16, 9, 64, MaterialOreSet.IRON))
                        .addParts(WIRE).addParts(RAW_METAL.ofAll(MaterialRawSet.GOLD))
                        .addParts(BLOCK.of(MaterialBlockSet.COPPER)).addParts(CABLE.of(CableTier.LV))
                        .addRecipes(ForgeHammerRecipes::apply, SmeltingRecipes::apply, StandardRecipes::apply));

        STEEL = MaterialRegistry.addMaterial(
                new MaterialBuilder("Steel", "steel")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x3f3f3f, common("ingot"), template("steel_ingot")))
                        .addParts(BOLT, RING, ROD, GEAR, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .addParts(ROD_MAGNETIC).addParts(BLOCK.of(MaterialBlockSet.IRON)).addParts(DRILL_HEAD, DRILL)
                        .addParts(MACHINE_CASING, MACHINE_CASING_PIPE).addParts(TANK.of(8)).addParts(BARREL.of(128))
                        .addParts(ExternalPart.of(HAMMER, MIItem.STEEL_HAMMER.getId().toString()))
                        .addRecipes(ForgeHammerRecipes::apply, SmeltingRecipes::apply, StandardRecipes::apply));

        LIGNITE_COAL = MaterialRegistry.addMaterial(new MaterialBuilder("Lignite Coal", "lignite_coal")
                        .set(MaterialProperty.MAIN_PART, GEM)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x644646))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_NON_METAL)
                .addParts(BLOCK.of(MaterialBlockSet.COAL)).addParts(GEM)
                .addParts(ORE.ofAll(UniformInt.of(0, 2), 25, 17, 256, MaterialOreSet.COAL))
                .addRecipes(ForgeHammerRecipes::apply, SmeltingRecipes::apply, StandardRecipes::apply).cancelRecipes("macerator/crushed_dust")
                .addRecipes(context -> new MIRecipeBuilder(context, MIMachineRecipeTypes.COMPRESSOR, "lignite_coal").addTaggedPartInput(DUST, 1).addPartOutput(GEM, 1))
                );

        ALUMINUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Aluminum", "aluminum")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x3fcaff, common("ingot"), template("aluminum_ingot")))
                        .addParts(BOLT, BLADE, RING, ROTOR, GEAR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .addParts(WIRE).addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(MACHINE_CASING.of("Advanced Machine Casing", "advanced_machine_casing"))
                        .addParts(DRILL_HEAD, DRILL).addParts(MACHINE_CASING_SPECIAL.of("Frostproof Machine Casing", "frostproof_machine_casing")).addParts(TANK.of(16))
                        .addParts(BARREL.of(512)).addParts(CABLE.of(CableTier.HV)).addRecipes(StandardRecipes::apply)
                        .addRecipes(SmeltingRecipes::applyBlastFurnace));

        BAUXITE = MaterialRegistry.addMaterial(new MaterialBuilder("Bauxite", "bauxite")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xC86400))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_NON_METAL)
                .addParts(BLOCK.of(MaterialBlockSet.LAPIS)).addParts(ORE.ofAll(UniformInt.of(1, 4), 24, 7, 32, MaterialOreSet.REDSTONE))
                .addRecipes(StandardRecipes::apply));

        LEAD = MaterialRegistry
                .addMaterial(new MaterialBuilder("Lead", "lead")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x6a76bc, common("ingot"), template("lead_ingot")))
                        .addParts(DOUBLE_INGOT, DUST, INGOT, NUGGET, PLATE, TINY_DUST).addParts(BLOCK.of(MaterialBlockSet.COPPER))
                        .addParts(ORE.ofAll(16, 5, 64, MaterialOreSet.IRON)).addParts(RAW_METAL.ofAll(MaterialRawSet.IRON))
                        .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply).cancelRecipes("macerator/raw_metal"));

        BATTERY_ALLOY = MaterialRegistry.addMaterial(new MaterialBuilder("Battery Alloy", "battery_alloy")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x9C7CA0))
                        .set(MaterialProperty.HARDNESS, SOFT)
                .addParts(TINY_DUST, DUST, INGOT, DOUBLE_INGOT, PLATE, CURVED_PLATE, NUGGET, LARGE_PLATE).addParts(BLOCK.of(MaterialBlockSet.IRON))
                .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        INVAR = MaterialRegistry
                .addMaterial(new MaterialBuilder("Invar", "invar")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xDCDC96))
                        .addParts(MACHINE_CASING_SPECIAL.of(
                                "Heatproof Machine Casing",
                                "heatproof_machine_casing"))
                        .addParts(TINY_DUST, DUST, INGOT, ROD, DOUBLE_INGOT, RING, BOLT, PLATE, LARGE_PLATE, NUGGET, GEAR)
                        .addParts(BLOCK.of(MaterialBlockSet.IRON)).addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        CUPRONICKEL = MaterialRegistry.addMaterial(new MaterialBuilder("Cupronickel", "cupronickel")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xE39681))
                        .set(MaterialProperty.HARDNESS, SOFT)
                .addParts(TINY_DUST, DUST, INGOT, DOUBLE_INGOT, PLATE, WIRE, NUGGET, WIRE_MAGNETIC).addParts(COIL)
                .addParts(BLOCK.of(MaterialBlockSet.COPPER)).addParts(CABLE.of(CableTier.MV))
                .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        ANTIMONY = MaterialRegistry.addMaterial(new MaterialBuilder("Antimony", "antimony")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xDCDCF0))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_METAL)
                .addParts(RAW_METAL.ofAll(MaterialRawSet.COPPER)).addParts(BLOCK.of(MaterialBlockSet.IRON))
                .addParts(ORE.ofAll(20, 5, 64, MaterialOreSet.REDSTONE)).addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        NICKEL = MaterialRegistry
                .addMaterial(new MaterialBuilder("Nickel", "nickel")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xFAFAC8))
                        .addParts(DOUBLE_INGOT, DUST, INGOT, NUGGET, PLATE, TINY_DUST)
                        .addParts(RAW_METAL.ofAll(MaterialRawSet.IRON)).addParts(BLOCK.of(MaterialBlockSet.IRON))
                        .addParts(ORE.ofAll(14, 6, 64, MaterialOreSet.IRON)).addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        SILVER = MaterialRegistry.addMaterial(
                new MaterialBuilder("Silver", "silver")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xDCDCFF, common("ingot"), template("silver_ingot")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(RAW_METAL.ofAll(MaterialRawSet.GOLD)).addParts(CABLE.of(CableTier.LV))
                        .addParts(DOUBLE_INGOT, DUST, INGOT, NUGGET, PLATE, TINY_DUST, WIRE).addParts(BLOCK.of(MaterialBlockSet.GOLD))
                        .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        SODIUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Sodium", "sodium")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x071CB8))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_NON_METAL).addParts(BLOCK.of(MaterialBlockSet.LAPIS))
                        .addParts(BATTERY).removeParts(CRUSHED_DUST).addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        SALT = MaterialRegistry.addMaterial(new MaterialBuilder("Salt", "salt")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xc7d6c5))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_NON_METAL)
                .addParts(BLOCK.of(MaterialBlockSet.REDSTONE)).addParts(ORE.ofAll(UniformInt.of(1, 3), 6, 6, 64, MaterialOreSet.COAL))
                .addRecipes(StandardRecipes::apply));

        TITANIUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Titanium", "titanium")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xDCA0F0, common("ingot"), template("titanium_ingot")))
                        .set(MaterialProperty.HARDNESS, HARD)
                        .addParts(BOLT, BLADE, RING, ROTOR, GEAR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                        .addParts(BLOCK.of(MaterialBlockSet.NETHERITE)).addParts(RAW_METAL.ofAll(MaterialRawSet.COPPER)).addParts(HOT_INGOT)
                        .addParts(MACHINE_CASING.of(
                                "Highly Advanced Machine Casing",
                                "highly_advanced_machine_casing")).addParts(DRILL_HEAD, DRILL).addParts(MACHINE_CASING_PIPE)
                        .addParts(MACHINE_CASING_SPECIAL.of("Solid Titanium Machine Casing", "solid_titanium_machine_casing")).addParts(ORE.of(MaterialOreSet.IRON))
                        .addParts(TANK.of(64)).addParts(BARREL.of(8192)).addRecipes(StandardRecipes::apply)
                        .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, true, 128, 400)).cancelRecipes("macerator/raw_metal"));

        ELECTRUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Electrum", "electrum")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xFFFF64, common("ingot"), template("electrum_ingot")))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(DOUBLE_INGOT, DUST, INGOT, NUGGET, PLATE, TINY_DUST).addParts(BLOCK.of(MaterialBlockSet.GOLD))
                        .addParts(WIRE, FINE_WIRE).addParts(CABLE.of(CableTier.MV)).addRecipes(StandardRecipes::apply, SmeltingRecipes::apply)
                        );

        SILICON = MaterialRegistry.addMaterial(new MaterialBuilder("Silicon", "silicon")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x3C3C50))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_METAL)
                .addParts(BLOCK.of(MaterialBlockSet.IRON)).addParts(N_DOPED_PLATE, P_DOPED_PLATE).addParts(PLATE, DOUBLE_INGOT, BATTERY)
                .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        STAINLESS_STEEL = MaterialRegistry.addMaterial(new MaterialBuilder("Stainless Steel", "stainless_steel")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xC8C8DC, common("ingot"), template("stainless_steel_ingot")))
                        .set(MaterialProperty.HARDNESS, HARD)
                .addParts(BLOCK.of(MaterialBlockSet.IRON))
                .addParts(BOLT, BLADE, RING, ROTOR, GEAR, ROD, CURVED_PLATE, DOUBLE_INGOT, DUST, INGOT, LARGE_PLATE, NUGGET, PLATE, TINY_DUST)
                .addParts(HOT_INGOT).addParts(DRILL_HEAD, DRILL).addParts(MACHINE_CASING.of(
                        "Turbo Machine Casing",
                        "turbo_machine_casing"))
                .addParts(MACHINE_CASING_PIPE).addParts(MACHINE_CASING_SPECIAL.of(
                        "Clean Stainless Steel Machine Casing",
                        "clean_stainless_steel_machine_casing"))
                .addParts(ROD_MAGNETIC).addParts(TANK.of(32)).addParts(BARREL.of(4096)).addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, true, 32, 400)).cancelRecipes("polarizer/rod_magnetic"));

        RUBY = MaterialRegistry
                .addMaterial(new MaterialBuilder("Ruby", "ruby")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xd1001f))
                        .set(MaterialProperty.HARDNESS, HARD).addParts(DUST, TINY_DUST).addRecipes(StandardRecipes::apply));

        CARBON = MaterialRegistry
                .addMaterial(
                        new MaterialBuilder("Carbon", "carbon")
                        .set(MaterialProperty.SET, DULL)
                                .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x444444))
                                .set(MaterialProperty.HARDNESS, SOFT).addParts(DUST, TINY_DUST, PLATE)
                                .addParts(
                                        LARGE_PLATE
                                                .withRegister((partContext, part, itemPath, itemId, itemTag) -> NuclearAbsorbable
                                                        .of("Carbon Large Plate", itemPath, 2500, 2 * NuclearConstant.BASE_HEAT_CONDUCTION,
                                                                INeutronBehaviour.of(NuclearConstant.ScatteringType.MEDIUM, NuclearConstant.CARBON,
                                                                        2),
                                                                NuclearConstant.DESINTEGRATION_BY_ROD * 2)))
                                .addRecipes(context -> new MIRecipeBuilder(context, MIMachineRecipeTypes.COMPRESSOR, "dust").addTaggedPartInput(DUST, 1)
                                        .addPartOutput(PLATE, 1))
                                .addRecipes(StandardRecipes::apply));

        CHROMIUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Chromium", "chromium")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xFFE6E6, common("ingot"), template("chromium_ingot")))
                        .addParts(CRUSHED_DUST).addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(ITEM_PURE_METAL).addParts(HOT_INGOT)
                        .addParts(PLATE, LARGE_PLATE, DOUBLE_INGOT).addRecipes(StandardRecipes::apply)
                        .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, true, 32, 400)).cancelRecipes("macerator/crushed_dust"));

        MANGANESE = MaterialRegistry.addMaterial(new MaterialBuilder("Manganese", "manganese")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xC1C1C1))
                .addParts(DUST, TINY_DUST, CRUSHED_DUST).addRecipes(StandardRecipes::apply).cancelRecipes("macerator/crushed_dust"));

        BERYLLIUM = MaterialRegistry.addMaterial(new MaterialBuilder("Beryllium", "beryllium")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x64B464))
                        .set(MaterialProperty.HARDNESS, HARD).addParts(BLOCK.of(MaterialBlockSet.NETHERITE))
                .addParts(DOUBLE_INGOT, DUST, INGOT, NUGGET, PLATE, TINY_DUST).addRecipes(StandardRecipes::apply));

        ANNEALED_COPPER = MaterialRegistry.addMaterial(new MaterialBuilder("Annealed Copper", "annealed_copper")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xff924f))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(ITEM_PURE_METAL)
                .addParts(BLOCK.of(MaterialBlockSet.COPPER)).addParts(PLATE, WIRE, DOUBLE_INGOT, HOT_INGOT).addParts(CABLE.of(CableTier.EV))
                .addRecipes(StandardRecipes::apply).addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, false, 64)));

        URANIUM = MaterialRegistry.addMaterial(new MaterialBuilder("Uranium", "uranium")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x39e600))
                .addParts(FUEL_ROD.ofAll(NuclearConstant.U))
                .addParts(ITEM_PURE_METAL).addParts(ROD).addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(ORE.ofAll(8, 5, 16, MaterialOreSet.COPPER))
                .addParts(RAW_METAL.of(MaterialRawSet.URANIUM))
                .addParts(RAW_METAL_BLOCK.of(MaterialRawSet.COPPER))
                .addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        URANIUM_235 = MaterialRegistry.addMaterial(
                new MaterialBuilder("Uranium 235", "uranium_235")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xe60045))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                        .addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(ITEM_PURE_METAL)
                        .addRecipes(StandardRecipes::apply).addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        URANIUM_238 = MaterialRegistry.addMaterial(
                new MaterialBuilder("Uranium 238", "uranium_238")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x55bd33))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(ITEM_PURE_METAL)
                        .addRecipes(StandardRecipes::apply).addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        LE_URANIUM = MaterialRegistry.addMaterial(new MaterialBuilder("LE Uranium", "le_uranium")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x70a33c))
                        .set(MaterialProperty.HARDNESS, VERY_HARD).addParts(FUEL_ROD.ofAll(NuclearConstant.LEU))
                .addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(ITEM_PURE_METAL).addParts(ROD).addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        HE_URANIUM = MaterialRegistry.addMaterial(new MaterialBuilder("HE Uranium", "he_uranium")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xaae838))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                .addParts(FUEL_ROD.ofAll(NuclearConstant.HEU))
                .addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(ITEM_PURE_METAL).addParts(ROD).addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        LE_MOX = MaterialRegistry.addMaterial(new MaterialBuilder("LE Mox", "le_mox")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x00e7e5))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                .addParts(BLOCK.of(MaterialBlockSet.GOLD))
                .addParts(FUEL_ROD.ofAll(NuclearConstant.LE_MOX)).addParts(ITEM_PURE_METAL).addParts(ROD).addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        HE_MOX = MaterialRegistry.addMaterial(new MaterialBuilder("HE Mox", "he_mox")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xcc87fa))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                .addParts(BLOCK.of(MaterialBlockSet.GOLD))
                .addParts(FUEL_ROD.ofAll(NuclearConstant.HE_MOX)).addParts(ITEM_PURE_METAL).addParts(ROD).addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        PLUTONIUM = MaterialRegistry.addMaterial(new MaterialBuilder("Plutonium", "plutonium")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xd701e7))
                        .set(MaterialProperty.HARDNESS, VERY_HARD).addParts(BLOCK.of(MaterialBlockSet.GOLD))
                .addParts(ITEM_PURE_METAL).addParts(BATTERY).addRecipes(StandardRecipes::apply)
                .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, 128)));

        PLATINUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Platinum", "platinum")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xffe5ba, common("ingot"), template("platinum_ingot")))
                        .addParts(BLOCK.of(MaterialBlockSet.GOLD)).addParts(RAW_METAL.ofAll(MaterialRawSet.GOLD))
                        .addParts(ORE.of(MaterialOreSet.GOLD)).addParts(ITEM_PURE_METAL)
                        .addParts(PLATE, DOUBLE_INGOT, WIRE, FINE_WIRE, HOT_INGOT).addParts(CABLE.of(CableTier.EV))
                        .addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, true, 128, 600)).addRecipes(StandardRecipes::apply)
                        .cancelRecipes("macerator/raw_metal"));

        KANTHAL = MaterialRegistry.addMaterial(
                new MaterialBuilder("Kanthal", "kanthal")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xcfcb00, common("ingot"), template("kanthal_ingot")))
                        .set(MaterialProperty.HARDNESS, HARD)
                        .addParts(TINY_DUST, DUST, PLATE, INGOT, NUGGET, WIRE, DOUBLE_INGOT, HOT_INGOT).addParts(COIL)
                        .addParts(BLOCK.of(MaterialBlockSet.COPPER)).addRecipes((ctx) -> SmeltingRecipes.applyBlastFurnace(ctx, true, 32, 400))
                        .addParts(CABLE.of(CableTier.HV)).addRecipes(StandardRecipes::apply));

        IRIDIUM = MaterialRegistry.addMaterial(
                new MaterialBuilder("Iridium", "iridium")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0xe1e6f5, common("ingot"), template("iridium_ingot")))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                        .addParts(BLOCK.of(MaterialBlockSet.DIAMOND)).addParts(ITEM_PURE_METAL).addParts(CURVED_PLATE)
                        .addParts(ORE.ofAll(10, 1, 16, MaterialOreSet.DIAMOND))
                        .addParts(MACHINE_CASING.of("Quantum Machine Casing!", "quantum_machine_casing", 6000f))

                        .addParts(TANK.of("Quantum Tank!", Integer.MAX_VALUE).withCustomPath("quantum_tank"))
                        .addParts(BARREL.of("Quantum Barrel!", Integer.MAX_VALUE).withCustomPath("quantum_barrel"))

                        .addParts(MACHINE_CASING_SPECIAL.of(
                                "Plasma Handling %s Machine Casing",
                                "plasma_handling_iridium_machine_casing", 6000f))
                        .addParts(MACHINE_CASING_PIPE.of(6000f))
                        .addParts(RAW_METAL.of(MaterialRawSet.IRIDIUM), PLATE)
                        .addParts(RAW_METAL_BLOCK.of(MaterialRawSet.GOLD))
                        .addRecipes(StandardRecipes::apply).addRecipes(SmeltingRecipes::apply)
                        .cancelRecipes("compressor/main")
                        .cancelRecipes("craft/tank")
                        .cancelRecipes("craft/barrel")
                        .cancelRecipes("assembler/tank")
                        .cancelRecipes("assembler/barrel"));

        MOZANITE = MaterialRegistry.addMaterial(new MaterialBuilder("Mozanite", "mozanite")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x96248e))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(CRUSHED_DUST, DUST, TINY_DUST)
                .addParts(BLOCK.of(MaterialBlockSet.REDSTONE)).addParts(ORE.ofAll(UniformInt.of(1, 4), 2, 3, 24, MaterialOreSet.LAPIS))
                .addRecipes(StandardRecipes::apply));

        CADMIUM = MaterialRegistry
                .addMaterial(new MaterialBuilder("Cadmium", "cadmium")
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x967224))
                        .set(MaterialProperty.HARDNESS, SOFT)
                        .addParts(DUST, TINY_DUST, INGOT, PLATE, ROD, DOUBLE_INGOT, BATTERY)
                        .addParts(
                                new RegularPart("Control Rod", FUEL_ROD.key)
                                        .withRegister((partContext, part, itemPath1, itemId, itemTag) -> NuclearAbsorbable
                                                        .of("Cadmium Control Rod", itemPath1, 1900, 0.5 * NuclearConstant.BASE_HEAT_CONDUCTION,
                                                                INeutronBehaviour.of(NuclearConstant.ScatteringType.HEAVY, NuclearConstant.CADMIUM,
                                                                        1),
                                                                NuclearConstant.DESINTEGRATION_BY_ROD))
                                        .withCustomFormattablePath("%s_control_rod"))
                        .addRecipes(StandardRecipes::apply, SmeltingRecipes::apply));

        NEODYMIUM = MaterialRegistry.addMaterial(new MaterialBuilder("Neodymium", "neodymium")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x1d4506))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(BLOCK.of(MaterialBlockSet.REDSTONE))
                .addParts(DUST, TINY_DUST).addRecipes(StandardRecipes::apply));

        YTTRIUM = MaterialRegistry.addMaterial(new MaterialBuilder("Yttrium", "yttrium")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, STONE)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x135166))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(BLOCK.of(MaterialBlockSet.REDSTONE))
                .addParts(DUST, TINY_DUST).addRecipes(StandardRecipes::apply));

        SUPERCONDUCTOR = MaterialRegistry.addMaterial(
                new MaterialBuilder("Superconductor", "superconductor")
                        .set(MaterialProperty.SET, SHINY)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x86e3ec, common("ingot"), template("superconductor_ingot")))
                        .set(MaterialProperty.HARDNESS, HARD)
                        .addParts(TINY_DUST, DUST, PLATE, INGOT, NUGGET, WIRE, DOUBLE_INGOT, HOT_INGOT).addParts(COIL)
                        .addParts(CABLE.of(CableTier.SUPERCONDUCTOR)).addRecipes(StandardRecipes::apply)
                        .cancelRecipes("craft/cable", "packer/cable",
                                "assembler/cable_synthetic_rubber",
                                "assembler/cable_styrene_rubber"));

        TUNGSTEN = MaterialRegistry.addMaterial(
                new MaterialBuilder("Tungsten", "tungsten")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Bakable(0x8760ad, common("ingot"), template("tungsten_ingot")))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                        .addParts(RAW_METAL.ofAll(MaterialRawSet.COPPER))
                        .addParts(TINY_DUST, DUST, PLATE, INGOT, NUGGET, LARGE_PLATE, DOUBLE_INGOT, ROD)
                        .addParts(BLOCK.of(MaterialBlockSet.NETHERITE)).addParts(ORE.ofAll(6, 5, 20, MaterialOreSet.IRON))
                        .addRecipes(StandardRecipes::apply));

        BLASTPROOF_ALLOY = MaterialRegistry
                .addMaterial(new MaterialBuilder("Blastproof Alloy", "blastproof_alloy")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x524c3a))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                        .addParts(INGOT, PLATE, LARGE_PLATE, CURVED_PLATE)
                        .addParts(MACHINE_CASING_SPECIAL.of(
                                "Blastproof Casing!",
                                "blastproof_casing", 6000f)).addRecipes(StandardRecipes::apply));

        NUCLEAR_ALLOY = MaterialRegistry.addMaterial(new MaterialBuilder("Nuclear Alloy", "nuclear_alloy")
                        .set(MaterialProperty.SET, METALLIC)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0x3d4d32))
                        .set(MaterialProperty.HARDNESS, VERY_HARD)
                .addParts(PLATE, LARGE_PLATE)
                .addParts(MACHINE_CASING_SPECIAL.of(
                        "%s Casing",
                        "nuclear_casing", 6000f)).addParts(MACHINE_CASING_PIPE.of(6000f))
                .addRecipes(StandardRecipes::apply));

        SOLDERING_ALLOY = MaterialRegistry.addMaterial(new MaterialBuilder("Soldering Alloy", "soldering_alloy")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xffabc4bf))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(DUST, TINY_DUST)
                .addParts(BLOCK.of(MaterialBlockSet.REDSTONE)).addRecipes(StandardRecipes::apply));

        SULFUR = MaterialRegistry.addMaterial(new MaterialBuilder("Sulfur", "sulfur")
                        .set(MaterialProperty.MAIN_PART, DUST)
                        .set(MaterialProperty.SET, DULL)
                        .set(MaterialProperty.COLORAMP, new ColorampParameters.Uniform(0xddb614))
                        .set(MaterialProperty.HARDNESS, SOFT).addParts(DUST, TINY_DUST)
                .addParts(BLOCK.of(MaterialBlockSet.REDSTONE)).addRecipes(StandardRecipes::apply));

        KubeJSFacade.instance.fireAddMaterialsEvent();
    }

}
