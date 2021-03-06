﻿using System.Collections.Generic;
using VRIS.Domain.Models;

namespace VRIS.Business.Repositories
{
    /// <summary>
    /// Repository interface
    /// </summary>
    /// <typeparam name="TModel"></typeparam>
    public interface IRepository<TModel>
        where TModel : IHasId, new()
    {
        /// <summary>
        /// List all items currently in the repository
        /// </summary>
        /// <returns></returns>
        IEnumerable<TModel> List();

        /// <summary>
        /// Create a new item, make sure the id is not set
        /// </summary>
        /// <param name="addItem"></param>
        /// <returns></returns>
        TModel Create(TModel addItem);

        /// <summary>
        /// Read an item by Id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        TModel Read(int id);

        /// <summary>
        /// Update an item, make sure it has an id
        /// </summary>
        /// <param name="setItem"></param>
        /// <returns></returns>
        TModel Update(TModel setItem);

        /// <summary>
        /// Remove an item by Id
        /// </summary>
        /// <param name="itemId"></param>
        /// <returns></returns>
        bool Delete(int itemId);
    }
}
