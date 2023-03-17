import PQ

def insertion_test(test_list):
    queue = PQ.create()
    for x in test_list:
        PQ.insert(queue, x)
    return queue

test_values = [5, 10, 16, 15, 27, 22, 25, 20, 17]
queue = insertion_test(test_values)
print(queue)
PQ.delete_min(queue)
print(queue)