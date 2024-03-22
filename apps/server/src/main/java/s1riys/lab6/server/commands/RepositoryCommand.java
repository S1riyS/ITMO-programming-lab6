package s1riys.lab6.server.commands;

import s1riys.lab6.server.managers.CollectionManager;

public abstract class RepositoryCommand extends Command {
    protected final CollectionManager collectionManager;

    public RepositoryCommand(String keyword, CollectionManager collectionManager) {
        super(keyword);
        this.collectionManager = collectionManager;
    }
}
