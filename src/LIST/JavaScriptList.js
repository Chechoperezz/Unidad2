function selectionSort(arr) {
    var n = arr.length;
    for (var i = 0; i < n-1; i++) {
        var minIndex = i;
        for (var j = i+1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        if (minIndex !== i) {
            var temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

var arr = [64, 34, 25, 12, 22, 11, 90];
selectionSort(arr);
console.log("Sorted array:");
console.log(arr.join(" "));
