--The fastest possible sorting algorithm! It runs in O(1) time but it has a low chance of working

function sort (n)
    return n
end

function printList (n)
    s = ""
    for _, i in ipairs(n) do
        s = s .. i .. " "
    end
    print(s)
end
  
list = {1, 4, 2, 6, 5, 7, 4, 3}
print("not sorted:")
printList(list)
list = sort(list) -- this fails BUT... it is fast
print("after sort:")
printList(list)

list2 = {1, 2, 5, 6, 7, 8, 10, 12, 45}
print("not sorted:")
printList(list2)
list2 = sort(list2) -- and just like that, constant time sort! (totally)
print("after sort:")
printList(list2)