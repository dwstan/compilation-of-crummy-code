"""That time I spent hours creating Karatsuba's to multiply 2 strings together only for it to 
reach the recursion limit. Then switching to the long multiplication only to learn that 
python handles big numbers for me.... I guess you learn something new everyday!"""


# uses Karatsuba's algorithm to multiply!
# did not work due to stack :( recursion limit reached...
def mult(x, y):
    def multHelp(x, y):
        if len(x) == 1 or len(y) == 1:
            return int(x) * int(y)
        m = min(len(x), len(y))
        a = x[: len(x) - m]
        b = x[len(x) - m :]
        c = y[: len(y) - m]
        d = y[len(y) - m :]

        e = multHelp(a, c)
        f = multHelp(b, d)
        g = multHelp(str(int(b) - int(a)), str(int(c) - int(d)))
        return (10 ** (2 * m) * e) + (10 ** m * (e + f + g) + f)

    return str(multHelp(x, y))


# the basic slow O(n*m) multiply way that doesnt abuse the ram :|
def multiply(num1, num2):
    len1 = len(num1)
    len2 = len(num2)
    if len1 == 0 or len2 == 0:
        return "0"

    result = [0] * (len1 + len2)

    i_n1 = 0
    i_n2 = 0

    for i in range(len1 - 1, -1, -1):
        carry = 0
        n1 = int(num1[i])
        i_n2 = 0
        for j in range(len2 - 1, -1, -1):
            n2 = int(num2[j])
            summ = n1 * n2 + result[i_n1 + i_n2] + carry
            carry = summ // 10
            result[i_n1 + i_n2] = summ % 10
            i_n2 += 1
        if carry > 0:
            result[i_n1 + i_n2] += carry
        i_n1 += 1

    i = len(result) - 1

    while i >= 0 and result[i] == 0:
        i -= 1

    if i == -1:
        return "0"

    s = ""
    while i >= 0:
        s += chr(result[i] + 48)
        i -= 1
    return s


print(mult("3", "12"))
print(multiply("3", "12"))

print(mult("5", "10"))
print(multiply("80132047123987509832174074987509832174324", "46234134123451241"))

# didn't even need to make these to multiply large numbers! THANKS PYTHON
print(
    50896706421347098327598321749832165932164832174324032197483217573219847324
    * 203274158314321
)
