#include <iostream>
#include <string>
using namespace std;

template <typename T>
void print(T t) { cout << t << endl; }

int main()
{
	const char *data;
	const char *s = "ABC";

	int x = 10;
	int *p = &x;
	int &j = x;

	int *k = new int(100);
	cout << int(2) << endl;

	printf("%p\n",&k); print( *k );
	printf("%p\n",&k); print( sizeof(*k) );

	printf("%p\n",&x); print( x );
	printf("%p\n",p); print( *p );
	printf("%p\n",&j); print( j );

	j = 122;
	printf("%p\n",&x); print( x );
	printf("%p\n",&j); print( j );
/*
	data = *s;

	printf("%p\n",&s[0]); print( *s );
	printf("%p\n",&s[0]); print( *data );

*/
	return 0;
}
