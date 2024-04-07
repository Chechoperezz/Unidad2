function quickSort(arr: number[], low: number, high: number): void {
    if (low < high) {
        const pi: number = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

function partition(arr: number[], low: number, high: number): number {
    const pivot: number = arr[high];
    let i: number = low - 1;
    for (let j: number = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            [arr[i], arr[j]] = [arr[j], arr[i]];
        }
    }
    [arr[i + 1], arr[high]] = [arr[high], arr[i + 1]];
    return i + 1;
}

let arr: number[] = [64, 34, 25, 12, 22, 11, 90];
quickSort(arr, 0, arr.length - 1);
console.log("Sorted array:");
console.log(arr.join(" "));
