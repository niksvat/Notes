
let a = [1, 2, 3, 4, 2, 6];


const main = async() => {

    //array
    let n = a.length;
    for(let i=0;i<n;i+=1){
        process.stdout.write(a[i]+" ");
    }

    let b = [...a];
    b[0] = 99;
    
    console.log(b);
    console.log(a);

    //hashmap
    console.log('---map---');
    const map = new Map();
    map.set('name', 'nikhil');
    map.set(1, 2);

    console.log(map.get('name'))
    console.log('size:'+map.size);
    for(const key of map.keys()){
        console.log(key+" "+map.get(key));
    }

    // hashset
    console.log('---set---');
    const set = new Set();
    set.add('niks');
    set.add(12);

    console.log('size:'+set.size);
    console.log('is "niks" present:'+set.has('niks'));
    for(const ele of set){
        console.log(ele);
    }


    //priorityQueue - https://dandkim.com/js-heap-implementation/
    // no standard lib present

};


(async () => {
    await main();
})();
