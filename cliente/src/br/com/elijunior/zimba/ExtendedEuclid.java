package br.com.elijunior.zimba;

import java.math.BigInteger;

public class ExtendedEuclid {

	//private TextView tvResultEuclides;

//	public void setTvResultEuclides(TextView tvResultEuclides) {
//		this.tvResultEuclides = tvResultEuclides;
//	}

//	public void solve(BigInteger a, BigInteger b) throws Exception {
//		BigInteger x = BigInteger.ZERO;
//		BigInteger y = BigInteger.ONE;
//		BigInteger lastx = BigInteger.ONE;
//		BigInteger lasty = BigInteger.ZERO;
//		BigInteger temp = BigInteger.ZERO;
//		while (!b.equals(BigInteger.ZERO)) {
//			BigInteger q = a.divide(b);
//			BigInteger r = a.mod(b);
//			a = b;
//			b = r;
//			temp = x;
//			x = lastx.subtract(q.multiply(x));
//			lastx = temp;
//			temp = y;
//			y = lastx.subtract(q.multiply(y));
//			lasty = temp;
//		}
//		tvResultEuclides.setText(String.valueOf("x = " + lastx + " y = "
//				+ lasty));
//	}

	public BigInteger[] extendedEuclid(BigInteger a, BigInteger b) throws Exception {
		BigInteger [ ] rtn = new BigInteger[3];
		if (b.equals (BigInteger.ZERO)) {
			rtn[0] = a;
			rtn[1] = BigInteger.ONE;
			rtn[2] = BigInteger.ZERO;
			return rtn;
		}
		rtn = extendedEuclid (b, a.mod (b));
		BigInteger x = rtn[1];
		BigInteger y = rtn[2];
		rtn[1] = y;
		rtn[2] = x.subtract (y.multiply (a.divide (b)));
		return rtn;
	}
}
