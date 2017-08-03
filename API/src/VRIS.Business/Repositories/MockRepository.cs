using System;
using System.Collections.Generic;
using System.Linq;
using VRIS.Domain.Models;

namespace VRIS.Business.Repositories
{
    /// <inheritdoc cref="IRepository{TModel}"/>
    public class MockRepository<TModel> : IRepository<TModel>
        where TModel : IHasId, new()
    {
        private readonly IList<TModel> _mockList;

        /// <inheritdoc cref="IRepository{TModel}"/>
        public MockRepository(IList<TModel> mockList)
        {
            _mockList = mockList;
        }

        /// <inheritdoc cref="IRepository{TModel}"/>
        public IEnumerable<TModel> List() => new List<TModel>(_mockList);

        /// <inheritdoc cref="IRepository{TModel}"/>
        public TModel Create(TModel addItem)
        {
            if (addItem.Id.HasValue) throw new ArgumentException(
                $"new {typeof(TModel).Name} items are not allowed to have an id", nameof(addItem.Id));
            addItem.Id = _mockList.Count;
            _mockList.Add(addItem);
            return addItem;
        }

        /// <inheritdoc cref="IRepository{TModel}"/>
        public TModel Read(int id) => _mockList.FirstOrDefault(item => item.Id.Equals(id));

        /// <inheritdoc cref="IRepository{TModel}"/>
        public TModel Update(TModel setItem)
        {
            if (!setItem.Id.HasValue) throw new ArgumentNullException(
                $"{typeof(TModel).Name} items require an Id", nameof(setItem.Id));

            var originalItem = _mockList.SingleOrDefault(item => setItem.Id.Equals(item.Id));
            if (originalItem == null) throw new ArgumentException(
                $"No {typeof(TModel).Name} item found with id {setItem.Id}", nameof(setItem.Id));

            var index = _mockList.IndexOf(originalItem);
            _mockList[index] = setItem;
            return setItem;
        }

        /// <inheritdoc cref="IRepository{TModel}"/>
        public bool Delete(int itemId)
        {
            var originalItem = _mockList.SingleOrDefault(item => itemId.Equals(item.Id));
            if (originalItem == null) throw new ArgumentException(
                $"No {typeof(TModel).Name} item found with id {itemId}", nameof(itemId));

            return _mockList.Remove(originalItem);
        }
    }
}
