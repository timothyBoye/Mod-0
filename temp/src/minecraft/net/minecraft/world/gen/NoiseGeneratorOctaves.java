package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class NoiseGeneratorOctaves extends NoiseGenerator {

   private NoiseGeneratorPerlin[] field_76307_a;
   private int field_76306_b;


   public NoiseGeneratorOctaves(Random p_i2111_1_, int p_i2111_2_) {
      this.field_76306_b = p_i2111_2_;
      this.field_76307_a = new NoiseGeneratorPerlin[p_i2111_2_];

      for(int var3 = 0; var3 < p_i2111_2_; ++var3) {
         this.field_76307_a[var3] = new NoiseGeneratorPerlin(p_i2111_1_);
      }

   }

   public double[] func_76304_a(double[] p_76304_1_, int p_76304_2_, int p_76304_3_, int p_76304_4_, int p_76304_5_, int p_76304_6_, int p_76304_7_, double p_76304_8_, double p_76304_10_, double p_76304_12_) {
      if(p_76304_1_ == null) {
         p_76304_1_ = new double[p_76304_5_ * p_76304_6_ * p_76304_7_];
      } else {
         for(int var14 = 0; var14 < p_76304_1_.length; ++var14) {
            p_76304_1_[var14] = 0.0D;
         }
      }

      double var27 = 1.0D;

      for(int var16 = 0; var16 < this.field_76306_b; ++var16) {
         double var17 = (double)p_76304_2_ * var27 * p_76304_8_;
         double var19 = (double)p_76304_3_ * var27 * p_76304_10_;
         double var21 = (double)p_76304_4_ * var27 * p_76304_12_;
         long var23 = MathHelper.func_76124_d(var17);
         long var25 = MathHelper.func_76124_d(var21);
         var17 -= (double)var23;
         var21 -= (double)var25;
         var23 %= 16777216L;
         var25 %= 16777216L;
         var17 += (double)var23;
         var21 += (double)var25;
         this.field_76307_a[var16].func_76308_a(p_76304_1_, var17, var19, var21, p_76304_5_, p_76304_6_, p_76304_7_, p_76304_8_ * var27, p_76304_10_ * var27, p_76304_12_ * var27, var27);
         var27 /= 2.0D;
      }

      return p_76304_1_;
   }

   public double[] func_76305_a(double[] p_76305_1_, int p_76305_2_, int p_76305_3_, int p_76305_4_, int p_76305_5_, double p_76305_6_, double p_76305_8_, double p_76305_10_) {
      return this.func_76304_a(p_76305_1_, p_76305_2_, 10, p_76305_3_, p_76305_4_, 1, p_76305_5_, p_76305_6_, 1.0D, p_76305_8_);
   }
}
